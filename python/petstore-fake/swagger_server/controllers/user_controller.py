import connexion
import six

from swagger_server.models.user import User  # noqa: E501
from swagger_server import util


def create_user(body):  # noqa: E501
    """Create user

    This can only be done by the logged in user. # noqa: E501

    :param body: Created user object
    :type body: dict | bytes

    :rtype: None
    """
    return {
        "id": 0,
        "username": "usertest",
        "firstName": "User",
        "lastName": "Test",
        "email": "user.test@mail.com",
        "password": "*****",
        "phone": "000-00000000",
        "userStatus": 1
    }


def create_users_with_array_input(body):  # noqa: E501
    """Creates list of users with given input array

     # noqa: E501

    :param body: List of user object
    :type body: list | bytes

    :rtype: None
    """
    return {
        "id": 0,
        "username": "usertest",
        "firstName": "User",
        "lastName": "Test",
        "email": "user.test@mail.com",
        "password": "*****",
        "phone": "000-00000000",
        "userStatus": 1
    }


def create_users_with_list_input(body):  # noqa: E501
    """Creates list of users with given input array

     # noqa: E501

    :param body: List of user object
    :type body: list | bytes

    :rtype: None
    """
    return {
        "id": 0,
        "username": "usertest",
        "firstName": "User",
        "lastName": "Test",
        "email": "user.test@mail.com",
        "password": "*****",
        "phone": "000-00000000",
        "userStatus": 1
    }


def delete_user(username):  # noqa: E501
    """Delete user

    This can only be done by the logged in user. # noqa: E501

    :param username: The name that needs to be deleted
    :type username: str

    :rtype: None
    """
    return 'ok'


def get_user_by_name(username):  # noqa: E501
    """Get user by user name

     # noqa: E501

    :param username: The name that needs to be fetched. Use user1 for testing. 
    :type username: str

    :rtype: User
    """
    return {
        "id": 0,
        "username": "usertest",
        "firstName": "User",
        "lastName": "Test",
        "email": "user.test@mail.com",
        "password": "*****",
        "phone": "000-00000000",
        "userStatus": 1
    }


def login_user(username, password):  # noqa: E501
    """Logs user into the system

     # noqa: E501

    :param username: The user name for login
    :type username: str
    :param password: The password for login in clear text
    :type password: str

    :rtype: str
    """
    return 'ok'


def logout_user():  # noqa: E501
    """Logs out current logged in user session

     # noqa: E501


    :rtype: None
    """
    return 'ok'


def update_user(body, username):  # noqa: E501
    """Updated user

    This can only be done by the logged in user. # noqa: E501

    :param body: Updated user object
    :type body: dict | bytes
    :param username: name that need to be deleted
    :type username: str

    :rtype: None
    """
    return 'ok'
