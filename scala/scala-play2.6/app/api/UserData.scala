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

import models.{ Category, User, Pet }

import collection.mutable.ListBuffer

class UserData {
  val users: ListBuffer[User] = new ListBuffer[User]()

  {
    users += User(1, "user1", "first name 1", "last name 1", "email1@test.com", "xxxx", "123-456-7890", 1)
    users += User(2, "user2", "first name 2", "last name 2", "email2@test.com", "xxxx", "123-456-7890", 2)
    users += User(3, "user3", "first name 3", "last name 3", "email3@test.com", "xxxx", "123-456-7890", 3)
    users += User(4, "user4", "first name 4", "last name 4", "email4@test.com", "xxxx", "123-456-7890", 1)
    users += User(5, "user5", "first name 5", "last name 5", "email5@test.com", "xxxx", "123-456-7890", 2)
    users += User(6, "user6", "first name 6", "last name 6", "email6@test.com", "xxxx", "123-456-7890", 3)
    users += User(7, "user7", "first name 7", "last name 7", "email7@test.com", "xxxx", "123-456-7890", 1)
    users += User(8, "user8", "first name 8", "last name 8", "email8@test.com", "xxxx", "123-456-7890", 2)
    users += User(9, "user9", "first name 9", "last name 9", "email9@test.com", "xxxx", "123-456-7890", 3)
    users += User(10, "user10", "first name 10", "last name 10", "email10@test.com", "xxxx", "123-456-7890", 1)
    users += User(11, "user?10", "first name ?10", "last name ?10", "email101@test.com", "xxxx", "123-456-7890", 1)

  }

  def findUserByName(username: String): Option[User] = {
    users.filter(user => user.username == username) match {
      case user if (user.size) > 0 => Some(user.head)
      case _ => None
    }
  }

  def addUser(user: User): Unit = {
    users --= users.filter(u => u.id == user.id)
    users += user
  }

  def removeUser(username: String): Unit = {
    for (user <- users) {
      if (user.username == username) {
        users -= user
      }
    }
  }
}
