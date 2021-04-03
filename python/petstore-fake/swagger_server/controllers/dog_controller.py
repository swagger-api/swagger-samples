import connexion
import six

from swagger_server.models.dog import Dog  # noqa: E501
from swagger_server import util


def add_dog(body):  # noqa: E501
    """Add a new dog to the store

     # noqa: E501

    :param body: Dog object that needs to be added to the store
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        body = Dog.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def delete_dog(dog_id, api_key=None):  # noqa: E501
    """Deletes a dog

     # noqa: E501

    :param dog_id: Dog id to delete
    :type dog_id: int
    :param api_key: 
    :type api_key: str

    :rtype: None
    """
    return 'do some magic!'


def get_dog_by_id(dog_id):  # noqa: E501
    """Find dog by ID

    Returns a single dog # noqa: E501

    :param dog_id: ID of dog to return
    :type dog_id: int

    :rtype: Dog
    """
    return 'do some magic!'


def update_dog(body):  # noqa: E501
    """Update an existing dog

     # noqa: E501

    :param body: Dog object that needs to be added.
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        body = Dog.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def update_dog_with_form(dog_id, name=None, status=None):  # noqa: E501
    """Updates a dog

     # noqa: E501

    :param dog_id: ID of dog that needs to be updated
    :type dog_id: int
    :param name: 
    :type name: str
    :param status: 
    :type status: str

    :rtype: None
    """
    return 'do some magic!'
