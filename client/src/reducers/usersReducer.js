import * as types from '../constants/userConstants.js';

export const usersReducer = (state = [], action) => {
  switch (action.type) {
    case types.ADD_USER:
      return [
        ...state,
        {
          ...action.payload,
          id: String(new Date().getTime())
        }
      ]

    case types.REMOVE_USER:
      return state.filter(user => user.id !== action.payload)

    case types.UPDATE_USER:
      return state.map(user => {
        if(user.id === action.payload.id){
          return {
            ...action.payload
          }
        }
        return user;
      })
      
    default:
      return state
  }
}
