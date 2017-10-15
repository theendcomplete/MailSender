package com.theendcomplete.MailSender

class Message {
    String messageRecipient
    String messageParam1
    String messageParam2
    String messageParam3
    String messageParam4

    static constraints = {
        messageRecipient(email: true, blank: false, nullable: false)
        messageParam1(size: 1..1000, blank: true, nullable: true)
        messageParam2(size: 1..1000, blank: true, nullable: true)
        messageParam3(size: 1..1000, blank: true, nullable: true)
        messageParam4(size: 1..1000, blank: true, nullable: true)
    }
}
