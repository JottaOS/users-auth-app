import { useReducer, useState } from "react";
import { UserForm } from "./components/UserForm";
import { UserList } from "./components/UserList";
import { usersReducer } from "./reducers/usersReducer";

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
  username: "",
  password: "",
  email: "",
};

export const UsersApp = () => {
  const [users, dispatch] = useReducer(usersReducer, initialUsers);
  const [selectedUser, setSelectedUser] = useState(initialUserForm);

  const handleAddUser = (user) => {
    dispatch({
      type: "addUser",
      payload: user,
    });
  };

  const handleRemoveUser = (id) => {
    dispatch({
      type: "removeUser",
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
                No hay usuarios en el sistema
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
