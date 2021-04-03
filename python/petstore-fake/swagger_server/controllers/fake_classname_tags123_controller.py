import connexion
import six

from swagger_server.models.client import Client  # noqa: E501
from swagger_server import util


def test_classname(body):  # noqa: E501
    """To test class name in snake case

     # noqa: E501

    :param body: client model
    :type body: dict | bytes

    :rtype: Client
    """
    if connexion.request.is_json:
        body = Client.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'
