# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.animal import Animal  # noqa: E501
from swagger_server.test import BaseTestCase


class TestAnimalController(BaseTestCase):
    """AnimalController integration test stubs"""

    def test_add_animal(self):
        """Test case for add_animal

        Add a new animal to the store
        """
        body = Animal()
        response = self.client.open(
            '/v2/animal',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_delete_animal(self):
        """Test case for delete_animal

        Deletes a animal
        """
        headers = [('api_key', 'api_key_example')]
        response = self.client.open(
            '/v2/animal/{animalId}'.format(animal_id=789),
            method='DELETE',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_get_animal_by_id(self):
        """Test case for get_animal_by_id

        Find animal by ID
        """
        response = self.client.open(
            '/v2/animal/{animalId}'.format(animal_id=789),
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_update_animal(self):
        """Test case for update_animal

        Update an existing animal
        """
        body = Animal()
        response = self.client.open(
            '/v2/animal',
            method='PUT',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_update_animal_with_form(self):
        """Test case for update_animal_with_form

        Updates a animal
        """
        data = dict(name='name_example',
                    status='status_example')
        response = self.client.open(
            '/v2/animal/{animalId}'.format(animal_id=789),
            method='POST',
            data=data,
            content_type='application/x-www-form-urlencoded')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
