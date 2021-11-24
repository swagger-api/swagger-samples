/**
 *  Copyright 2014 Reverb Technologies, Inc.
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package api

import collection.mutable.ListBuffer

import models.Order
import java.util.Date

class StoreData {
  val orders: ListBuffer[Order] = new ListBuffer[Order]()

  orders += Order(1, 1, 2, new Date(), "placed")
  orders += Order(2, 1, 2, new Date(), "delivered")
  orders += Order(3, 2, 2, new Date(), "placed")
  orders += Order(4, 2, 2, new Date(), "delivered")
  orders += Order(5, 3, 2, new Date(), "placed")

  def findOrderById(orderId: Long): Option[Order] = {
    orders.filter(order => order.id == orderId) match {
      case orders if (orders.size) > 0 => Some(orders.head)
      case _ => None
    }
  }

  def placeOrder(order: Order) = {
    // remove any orders with same id
    orders --= orders.filter(o => o.id == order.id)
    orders += order
  }

  def deleteOrder(orderId: Long) = orders --= orders.filter(o => o.id == orderId)
}
