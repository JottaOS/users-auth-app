import PropTypes from "prop-types";

export const UserRow = ({
  id,
  username,
  email,
  password,
  handleRemoveUser,
  handleSelectedUserForm,
}) => {
  return (
    <tr>
      <td>{id}</td>
      <td>{username}</td>
      <td>{email}</td>
      <td>
        <button
          type="button"
          className="btn btn-secondary btn-sm"
          onClick={() =>
            handleSelectedUserForm({ id, username, email, password })
          }
        >
          Update
        </button>
      </td>
      <td>
        <button
          type="button"
          className="btn btn-danger btn-sm"
          onClick={() => handleRemoveUser(id)}
        >
          Remove
        </button>
      </td>
    </tr>
  );
};

UserRow.propTypes = {
  id: PropTypes.string.isRequired,
  username: PropTypes.string.isRequired,
  email: PropTypes.string.isRequired,
  password: PropTypes.string.isRequired,
  handleRemoveUser: PropTypes.func.isRequired,
  handleSelectedUserForm: PropTypes.func.isRequired,
};
