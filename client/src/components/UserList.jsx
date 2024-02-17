import { UserRow } from "./UserRow";
import PropTypes from "prop-types";

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
            users.map(({ id, username, email }) => (
              <UserRow
                key={id}
                id={id}
                username={username}
                email={email}
                handleRemoveUser={handleRemoveUser}
                handleSelectedUserForm={handleSelectedUserForm}
              />
            ))
          ) : (
            <i>The list is empty</i>
          )}
        </tbody>
      </table>
    </>
  );
};

UserList.propTypes = {
  users: PropTypes.array.isRequired,
  handleRemoveUser: PropTypes.func.isRequired,
  handleSelectedUserForm: PropTypes.func.isRequired,
};
