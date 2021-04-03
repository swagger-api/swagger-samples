import connexion
import six

from swagger_server.models.client import Client  # noqa: E501
from swagger_server import util


def test_special_tags(body):  # noqa: E501
    """To test special tags

    To test special tags # noqa: E501

    :param body: client model
    :type body: dict | bytes

    :rtype: Client
    """
    if connexion.request.is_json:
        body = Client.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'
