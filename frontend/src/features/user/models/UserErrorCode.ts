enum UserErrorCode{
    USERNAME_TOO_SHORT = "USERNAME_TOO_SHORT",
    USERNAME_EMPTY = "USERNAME_EMPTY",
    USERNAME_EXISTS = "USERNAME_EXISTS",
    USERNAME_INVALID = "USERNAME_INVALID",

    EMAIL_EMPTY = "EMAIL_EMPTY",
    EMAIL_INVALID_FORMAT = "EMAIL_INVALID_FORMAT",
    EMAIL_EXISTS = "EMAIL_EXISTS",

    PHONE_EMPTY = "PHONE_EMPTY",
}
export default UserErrorCode