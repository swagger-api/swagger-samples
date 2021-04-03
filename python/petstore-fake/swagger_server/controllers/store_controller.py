import connexion
import six

from swagger_server.models.order import Order  # noqa: E501
from swagger_server import util


def delete_order(order_id):  # noqa: E501
    """Delete purchase order by ID

    For valid response try integer IDs with value &lt; 1000. Anything above 1000 or nonintegers will generate API errors # noqa: E501

    :param order_id: ID of the order that needs to be deleted
    :type order_id: str

    :rtype: None
    """
    return 'ok'


def get_inventory():  # noqa: E501
    """Returns pet inventories by status

    Returns a map of status codes to quantities # noqa: E501


    :rtype: Dict[str, int]
    """
    response = dict()
    return response


def get_order_by_id(order_id):  # noqa: E501
    """Find purchase order by ID

    For valid response try integer IDs with value &lt;&#x3D; 5 or &gt; 10. Other values will generated exceptions # noqa: E501

    :param order_id: ID of pet that needs to be fetched
    :type order_id: int

    :rtype: Order
    """
    return {
        "id": order_id,
        "petId": 1000,
        "quantity": 10,
        "shipDate": "2021-04-03T10:00:02.756Z",
        "status": "placed",
        "complete": False
    }


def place_order(body):  # noqa: E501
    """Place an order for a pet

     # noqa: E501

    :param body: order placed for purchasing the pet
    :type body: dict | bytes

    :rtype: Order
    """
    return {
        "id": 100,
        "petId": 1000,
        "quantity": 10,
        "shipDate": "2021-04-03T10:00:02.756Z",
        "status": "placed",
        "complete": False
    }

