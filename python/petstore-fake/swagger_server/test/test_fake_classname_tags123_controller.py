# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.client import Client  # noqa: E501
from swagger_server.test import BaseTestCase


class TestFakeClassnameTags123Controller(BaseTestCase):
    """FakeClassnameTags123Controller integration test stubs"""

    def test_test_classname(self):
        """Test case for test_classname

        To test class name in snake case
        """
        body = Client()
        response = self.client.open(
            '/v2/fake_classname_test',
            method='PATCH',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
