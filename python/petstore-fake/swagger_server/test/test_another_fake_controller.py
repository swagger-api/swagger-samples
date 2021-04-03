# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.client import Client  # noqa: E501
from swagger_server.test import BaseTestCase


class TestAnotherFakeController(BaseTestCase):
    """AnotherFakeController integration test stubs"""

    def test_test_special_tags(self):
        """Test case for test_special_tags

        To test special tags
        """
        body = Client()
        response = self.client.open(
            '/v2/another-fake/dummy',
            method='PATCH',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
