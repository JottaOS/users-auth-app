import { useReducer, useState } from "react";
import { usersReducer } from "../reducers/usersReducer";
import * as types from "../constants/userConstants.js";

const initialUsers = [
  {
    id: 1,
    username: "juan",
    password: "12345",
    email: "juan@correo.com",
  },
  {
    id: 2,
    username: "franco",
    password: "12345",
    email: "franco@correo.com",
  },
];

const initialUserForm = {
  id: 0,
  username: "",
  password: "",
  email: "",
};

export const useUsers = () => {
  const [users, dispatch] = useReducer(usersReducer, initialUsers);
  const [selectedUser, setSelectedUser] = useState(initialUserForm);

  const handleAddUser = (user) => {
    const type = user.id ? types.UPDATE_USER : types.ADD_USER;
    dispatch({
      type,
      payload: user,
    });
  };

  const handleRemoveUser = (id) => {
    dispatch({
      type: types.REMOVE_USER,
      payload: id,
    });
  };

  const handleSelectedUserForm = (user) => {
    setSelectedUser({ ...user });
  };

  return {
    users,
    selectedUser,
    initialUserForm,

    handleAddUser,
    handleRemoveUser,
    handleSelectedUserForm,
  };
};
