import connexion
import six
import random

from swagger_server.models.all_pets_response import AllPetsResponse  # noqa: E501
from swagger_server.models.api_response import ApiResponse  # noqa: E501
from swagger_server.models.pet import Pet  # noqa: E501
from swagger_server.models.single_pet_response import SinglePetResponse  # noqa: E501
from swagger_server.models.sub_category import SubCategory  # noqa: E501
from swagger_server import util

pets = dict()
def add_pet(body):  # noqa: E501
    """Add a new pet to the store

     # noqa: E501

    :param body: Pet object that needs to be added to the store
    :type body: dict | bytes

    :rtype: None
    """
    if not "id" in body:
        body["id"] = random.randrange(1000, 1000000, 3)
    pets[body["id"]] = body
    return 'ok'


def delete_pet(pet_id, api_key=None):  # noqa: E501
    """Deletes a pet

     # noqa: E501

    :param pet_id: Pet id to delete
    :type pet_id: int
    :param api_key: 
    :type api_key: str

    :rtype: None
    """
    del pets[pet_id]
    return 'ok'


def do_category_stuff(body=None):  # noqa: E501
    """do_category_stuff

     # noqa: E501

    :param body: 
    :type body: dict | bytes

    :rtype: ApiResponse
    """
    if connexion.request.is_json:
        body = SubCategory.from_dict(connexion.request.get_json())  # noqa: E501
    response = dict()
    response["code"] = 200
    response["message"] = "ok"
    response["type"] = "ok"
    return response


def find_pets_by_status(status):  # noqa: E501
    """Finds Pets by status

    Multiple status values can be provided with comma separated strings # noqa: E501

    :param status: Status values that need to be considered for filter
    :type status: List[str]

    :rtype: List[Pet]
    """
    pet_list_response = list()
    pet_ids = list(pets)
    for id in pet_ids:
        pet = pets[id]
        for st in status:
            if st == pet["status"]:
                pet_list_response.append(pet)
    return pet_list_response


def find_pets_by_tags(tags):  # noqa: E501
    """Finds Pets by tags

    Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing. # noqa: E501

    :param tags: Tags to filter by
    :type tags: List[str]

    :rtype: List[Pet]
    """
    pet_dict_response = dict()
    pet_ids = list(pets)
    for id in pet_ids:
        pet = pets[id]
        for tag in tags:
            pet_tags = pet["tags"]
            for pet_tag in pet_tags:
                if tag == pet_tag["name"]:
                    pet_dict_response[id] = pet
    return list(pet_dict_response.values())


def get_all_pets():  # noqa: E501
    """get_all_pets

     # noqa: E501


    :rtype: AllPetsResponse
    """
    pet = get_random_pet()
    pets = list()
    pets.append(pet)
    return pets


def get_pet_by_id(pet_id):  # noqa: E501
    """Find pet by ID

    Returns a single pet # noqa: E501

    :param pet_id: ID of pet to return
    :type pet_id: int

    :rtype: Pet
    """
    return pets[pet_id]


def get_random_pet():  # noqa: E501
    """get_random_pet

     # noqa: E501


    :rtype: SinglePetResponse
    """
    cat = dict()
    cat["class_name"] = "just a cat"
    cat["color"] = "black"
    cat["declawed"] = True

    pet = dict()
    pet["pet"] = cat

    return pet


def update_pet(body):  # noqa: E501
    """Update an existing pet

     # noqa: E501

    :param body: Pet object that needs to be added to the store
    :type body: dict | bytes

    :rtype: None
    """
    pets[body["id"]] = body
    return 'ok'


def update_pet_with_form(pet_id, name=None, status=None):  # noqa: E501
    """Updates a pet in the store with form data

     # noqa: E501

    :param pet_id: ID of pet that needs to be updated
    :type pet_id: int
    :param name: 
    :type name: str
    :param status: 
    :type status: str

    :rtype: None
    """
    return 'ok'


def upload_file(pet_id, additional_metadata=None, file=None):  # noqa: E501
    """uploads an image

     # noqa: E501

    :param pet_id: ID of pet to update
    :type pet_id: int
    :param additional_metadata: 
    :type additional_metadata: str
    :param file: 
    :type file: strstr

    :rtype: ApiResponse
    """
    return 'ok'
