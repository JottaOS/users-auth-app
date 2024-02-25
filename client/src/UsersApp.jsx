import { UserForm } from "./components/UserForm";
import { UserList } from "./components/UserList";
import { useUsers } from "./hooks/useUsers";

export const UsersApp = () => {
  const {
    users,
    selectedUser,
    initialUserForm,
    handleAddUser,
    handleRemoveUser,
    handleSelectedUserForm,
  } = useUsers();

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
