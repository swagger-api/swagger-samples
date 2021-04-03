# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.body2 import Body2  # noqa: E501
from swagger_server.models.body3 import Body3  # noqa: E501
from swagger_server.models.body4 import Body4  # noqa: E501
from swagger_server.models.body5 import Body5  # noqa: E501
from swagger_server.models.client import Client  # noqa: E501
from swagger_server.models.outer_boolean import OuterBoolean  # noqa: E501
from swagger_server.models.outer_composite import OuterComposite  # noqa: E501
from swagger_server.models.outer_number import OuterNumber  # noqa: E501
from swagger_server.models.outer_string import OuterString  # noqa: E501
from swagger_server.test import BaseTestCase


class TestFakeController(BaseTestCase):
    """FakeController integration test stubs"""

    def test_fake_outer_boolean_serialize(self):
        """Test case for fake_outer_boolean_serialize

        
        """
        body = True
        response = self.client.open(
            '/v2/fake/outer/boolean',
            method='POST',
            data=json.dumps(body),
            content_type='*/*')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_fake_outer_composite_serialize(self):
        """Test case for fake_outer_composite_serialize

        
        """
        body = OuterComposite()
        response = self.client.open(
            '/v2/fake/outer/composite',
            method='POST',
            data=json.dumps(body),
            content_type='*/*')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_fake_outer_number_serialize(self):
        """Test case for fake_outer_number_serialize

        
        """
        body = 3.4
        response = self.client.open(
            '/v2/fake/outer/number',
            method='POST',
            data=json.dumps(body),
            content_type='*/*')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_fake_outer_string_serialize(self):
        """Test case for fake_outer_string_serialize

        
        """
        body = 'body_example'
        response = self.client.open(
            '/v2/fake/outer/string',
            method='POST',
            data=json.dumps(body),
            content_type='*/*')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_test_client_model(self):
        """Test case for test_client_model

        To test \"client\" model
        """
        body = Client()
        response = self.client.open(
            '/v2/fake',
            method='PATCH',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_test_endpoint_parameters(self):
        """Test case for test_endpoint_parameters

        Fake endpoint for testing various parameters 假端點 偽のエンドポイント 가짜 엔드 포인트 
        """
        body = Body3()
        response = self.client.open(
            '/v2/fake',
            method='POST',
            data=json.dumps(body),
            content_type='application/xml; charset=utf-8')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_test_enum_parameters(self):
        """Test case for test_enum_parameters

        To test enum parameters
        """
        query_string = [('enum_query_string_array', '$'),
                        ('enum_query_string', '-efg'),
                        ('enum_query_integer', 56)]
        headers = [('enum_header_string_array', '$'),
                   ('enum_header_string', '-efg')]
        response = self.client.open(
            '/v2/fake',
            method='GET',
            headers=headers,
            query_string=query_string)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_test_enum_request_body(self):
        """Test case for test_enum_request_body

        To test enum parameters
        """
        body = Body4()
        response = self.client.open(
            '/v2/fake/enum/form',
            method='POST',
            data=json.dumps(body),
            content_type='*/*')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_test_inline_additional_properties(self):
        """Test case for test_inline_additional_properties

        test inline additionalProperties
        """
        body = None
        response = self.client.open(
            '/v2/fake/inline-additionalProperties',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_test_json_form_data(self):
        """Test case for test_json_form_data

        test json serialization of form data
        """
        body = Body5()
        response = self.client.open(
            '/v2/fake/jsonFormData',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
