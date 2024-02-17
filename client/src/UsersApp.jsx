import { useReducer, useState } from "react";
import { UserForm } from "./components/UserForm";
import { UserList } from "./components/UserList";
import { usersReducer } from "./reducers/usersReducer";
import * as types from "./constants/userConstants.js";

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
  id: "",
  username: "",
  password: "",
  email: "",
};

export const UsersApp = () => {
  const [users, dispatch] = useReducer(usersReducer, initialUsers);
  const [selectedUser, setSelectedUser] = useState(initialUserForm);

  const handleAddUser = (user) => {
    if (user.id) {
      dispatch({
        type: types.UPDATE_USER,
        payload: user,
      });
      return;
    }

    dispatch({
      type: types.ADD_USER,
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

  return (
    <>
      <div className="container my-4">
        <h2>Users app</h2>
        <div className="row">
          <div className="col">
            <UserForm
              initialUserForm={initialUserForm}
              selectedUser={selectedUser}
              handleAddUser={handleAddUser}
            />
          </div>
          <div className="col">
            {users.length === 0 ? (
              <div className="alert alert-warning">
                There are no users registered.
              </div>
            ) : (
              <UserList
                users={users}
                handleRemoveUser={handleRemoveUser}
                handleSelectedUserForm={handleSelectedUserForm}
              />
            )}
          </div>
        </div>
      </div>
    </>
  );
};
