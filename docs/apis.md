# Project document

## 1. Backend api list
-----------------------------------------------------------------------------------------------------------------------------

| resource                        | action              | route                                 | method    | authorization   |
| ------------------------------- | ------------------- | ------------------------------------- | --------- | --------------- |
| categories                      | list                | api/categories                        | get       | admin, user     | 
|                                 | get                 | api/categories                        | get       | admin, user     |
|                                 | create              | api/categories                        | post      | admin           |
|                                 | update              | api/categories                        | put       | admin           |
|                                 | delete              | api/categories                        | delete    | admin           |
| items                           | list                | api/items                             | get       | admin, user     |
|                                 | get                 | api/items                             | get       | admin, user     |
|                                 | create              | api/items                             | post      | admin           |
|                                 | update              | api/items                             | put       | admin           |
|                                 | delete              | api/items                             | delete    | admin           |
| categories/{category_id}/items  | list                | api/categories/{category_id}/items    | get       | admin, user     |
|                                 | create              | api/categories/{category_id}/items    | post      | admin           |
| orders                          | list orders of user | api/users/orders                      | get       | admin, user     |
|                                 | creat               | api/users/orders                      | post      | admin, user     |
|                                 | list                | api/orders                            | post      | admin           |
|                                 | get                 | api/orders/{orderId}                  | post      | admin, user     |
|                                 | cancel              | api/orders/{orderId}/cancel           | patch     | admin           |
|                                 | pay                 | api/orders/{orderId}/pay              | patch     | user            |
|                                 | start processing    | api/orders/{orderId}/start-processing | patch     | admin           |
|                                 | start shipping      | api/orders/{orderId}/ship             | patch     | admin           |
|                                 | shipped             | api/orders/{orderId}/finish-shipping  | patch     | admin           |
| users                           | sign-in             | api/auth                              | post      | admin, user     |
|                                 | parse api token     | api/auth/parse                        | post      | admin           |
|                                 | get signed in user  | api/auth/user                         | get       | admin, user     |
|                                 | list all users      | api/users                             | get       | admin           |
|                                 | get                 | api/users/{username}                  | get       | admin           |
|                                 | create              | api/users                             | post      | admin, user     |
|                                 | change username     | api/users/username                    | patch     | admin, user     |
|                                 | change password     | api/users/password                    | patch     | admin, user     |
|                                 | change email        | api/users/email                       | patch     | admin, user     |
|                                 | change phone        | api/users/phone                       | patch     | admin, user     |
|                                 | delete              | api/users/{username}                  | delete    | admin, user     |
| cart                            | list                | api/users/cart                        | get       | user            |
|                                 | add items           | api/users/cart                        | post      | user            |
|                                 | remove items        | api/users/cart                        | delete    | user            |
| storage                         | list                | storage                               | get       | admin           |
|                                 | get                 | storage/{filename}                    | get       | admin, user     |
|                                 | upload file         | storage                               | post      | admin           |