/**
 * Copyright (C) 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gwidgets.errai.tutorial.client.shared;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Models a contact in an address book.
 * {@link Portable} allows instances of this class to be serialized. This allows {@link Contact} instances to be used as
 * parameters or return values of Errai RPC methods. It also allows {@link Contact} instances to be fired and observed
 * as Errai CDI events between client and server.
 * <p>
 */
@Portable
public class Contact {

  public static final String ALL_CONTACTS_QUERY = "allContacts";

  private long id;

  private String fullname;

  private String nickname;


  public Contact(final @MapsTo("fullname") String fullname, final  @MapsTo("nickname") String nickname) {
    this.fullname = fullname;
    this.nickname = nickname;
  }

  public String getFullname() {
    return fullname;
  }

//  public void setFullname(String fullname) {
//    this.fullname = fullname;
////  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNickname() {
    return nickname;
  }

//  public void setNickname(String nickname) {
//    this.nickname = nickname;
//  }

  @Override
  public String toString() {
    return "Contact{" +
            "fullname='" + fullname + '\'' +
            ", id=" + id +
            ", nickname='" + nickname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Contact)) return false;

    Contact contact = (Contact) o;

    if (getId() != contact.getId()) return false;
    if (getFullname() != null ? !getFullname().equals(contact.getFullname()) : contact.getFullname() != null)
      return false;
    return getNickname() != null ? getNickname().equals(contact.getNickname()) : contact.getNickname() == null;

  }

  @Override
  public int hashCode() {
    int result = (int) (getId() ^ (getId() >>> 32));
    result = 31 * result + (getFullname() != null ? getFullname().hashCode() : 0);
    result = 31 * result + (getNickname() != null ? getNickname().hashCode() : 0);
    return result;
  }
}
