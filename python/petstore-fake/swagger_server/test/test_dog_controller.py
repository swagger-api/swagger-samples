# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.dog import Dog  # noqa: E501
from swagger_server.test import BaseTestCase


class TestDogController(BaseTestCase):
    """DogController integration test stubs"""

    def test_add_dog(self):
        """Test case for add_dog

        Add a new dog to the store
        """
        body = Dog()
        response = self.client.open(
            '/v2/dog',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_delete_dog(self):
        """Test case for delete_dog

        Deletes a dog
        """
        headers = [('api_key', 'api_key_example')]
        response = self.client.open(
            '/v2/dog/{dogId}'.format(dog_id=789),
            method='DELETE',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_get_dog_by_id(self):
        """Test case for get_dog_by_id

        Find dog by ID
        """
        response = self.client.open(
            '/v2/dog/{dogId}'.format(dog_id=789),
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_update_dog(self):
        """Test case for update_dog

        Update an existing dog
        """
        body = Dog()
        response = self.client.open(
            '/v2/dog',
            method='PUT',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_update_dog_with_form(self):
        """Test case for update_dog_with_form

        Updates a dog
        """
        data = dict(name='name_example',
                    status='status_example')
        response = self.client.open(
            '/v2/dog/{dogId}'.format(dog_id=789),
            method='POST',
            data=data,
            content_type='application/x-www-form-urlencoded')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
