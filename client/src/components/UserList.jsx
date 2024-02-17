import { UserRow } from "./UserRow";

export const UserList = ({
  users = [],
  handleRemoveUser,
  handleSelectedUserForm,
}) => {
  return (
    <>
      <table className="table table-hover tabe-striped">
        <thead>
          <tr>
            <th>#</th>
            <th>USERNAME</th>
            <th>EMAIL</th>
            <th>UPDATE</th>
            <th>REMOVE</th>
          </tr>
        </thead>
        <tbody>
          {users.length ? (
            users.map(({ id, username, email, password }) => (
              <UserRow
                key={id}
                id={id}
                username={username}
                email={email}
                password={password}
                handleRemoveUser={handleRemoveUser}
                handleSelectedUserForm={handleSelectedUserForm}
              />
            ))
          ) : (
            <i>La lista de usuarios está vacía</i>
          )}
        </tbody>
      </table>
    </>
  );
};
