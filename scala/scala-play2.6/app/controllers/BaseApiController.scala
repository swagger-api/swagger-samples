package controllers

import akka.stream.scaladsl.Source
import akka.util.ByteString
import util.RestResourceUtil
import io.swagger.util.Json
import play.api.http.HttpEntity
import org.reactivestreams._
import play.api.libs.iteratee.Enumerator
import play.api.libs.iteratee.streams.IterateeStreams
import play.api.mvc._

class BaseApiController extends Controller with RestResourceUtil {
  // APIs
  protected def JsonResponse(data: Object) = {
    val jsonValue: String = toJsonString(data)

    val bodyEnumerator = Enumerator(ByteString.fromArray(jsonValue.getBytes()))
    val bodyPublisher: Publisher[ByteString] = IterateeStreams.enumeratorToPublisher(bodyEnumerator)
    val bodySource: Source[ByteString, _] = Source.fromPublisher(bodyPublisher)
    val entity: HttpEntity = HttpEntity.Streamed(bodySource, null, Some("application/json"))

    new Result(header = ResponseHeader(200), body = entity).as("application/json")
      .withHeaders(
        ("Access-Control-Allow-Origin", "*"),
        ("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT"),
        ("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization"))
  }

  protected def JsonResponse(data: Object, code: Int) = {
    val jsonValue: String = toJsonString(data)
    val bodyEnumerator = Enumerator(ByteString.fromArray(jsonValue.getBytes()))
    val bodyPublisher: Publisher[ByteString] = IterateeStreams.enumeratorToPublisher(bodyEnumerator)
    val bodySource: Source[ByteString, _] = Source.fromPublisher(bodyPublisher)
    val entity: HttpEntity = HttpEntity.Streamed(bodySource, null, Some("application/json"))



    new Result(header = ResponseHeader(code), body = entity).as("application/json")
      .withHeaders(
        ("Access-Control-Allow-Origin", "*"),
        ("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT"),
        ("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization"))
  }

  def toJsonString(data: Any): String = {
    if (data.getClass.equals(classOf[String])) {
      data.asInstanceOf[String]
    } else {
      Json.pretty(data)
    }
  }
}
