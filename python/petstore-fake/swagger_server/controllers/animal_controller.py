import connexion
import six

from swagger_server.models.animal import Animal  # noqa: E501
from swagger_server import util


def add_animal(body):  # noqa: E501
    """Add a new animal to the store

     # noqa: E501

    :param body: Animal object that needs to be added to the store
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        body = Animal.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def delete_animal(animal_id, api_key=None):  # noqa: E501
    """Deletes a animal

     # noqa: E501

    :param animal_id: Animal id to delete
    :type animal_id: int
    :param api_key: 
    :type api_key: str

    :rtype: None
    """
    return 'do some magic!'


def get_animal_by_id(animal_id):  # noqa: E501
    """Find animal by ID

    Returns a single animal # noqa: E501

    :param animal_id: ID of pet to return
    :type animal_id: int

    :rtype: Animal
    """
    return 'do some magic!'


def update_animal(body):  # noqa: E501
    """Update an existing animal

     # noqa: E501

    :param body: Animal object that needs to be added.
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        body = Animal.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def update_animal_with_form(animal_id, name=None, status=None):  # noqa: E501
    """Updates a animal

     # noqa: E501

    :param animal_id: ID of animal that needs to be updated
    :type animal_id: int
    :param name: 
    :type name: str
    :param status: 
    :type status: str

    :rtype: None
    """
    return 'do some magic!'
