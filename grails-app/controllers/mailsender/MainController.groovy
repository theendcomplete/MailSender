package mailsender

import com.theendcomplete.MailSender.ExcelMessageImporter
import com.theendcomplete.MailSender.Message

class MainController {
    static scope = "session"
    def messageList = []
    def messagingService

    def index() {

//        if (params.messageList) {
//            messageList = params.messageList
//        }

        [messageList: messageList]

        //        10.times {
//            messageList.add(new Message(messageRecipient: "nick" + it + "@dances.ru", messageParam1: "param1", messageParam2: "param2", messageParam3: "param 3", messageParam4: "param 4").save(flush: true))
//        }


    }


    def parseExcel() {
//        def file = request.getFile('file')

//        def file = request.getFile('file')
//        if (!file.empty) {
//            def sheetheader = []
//            def values = []
//            def workbook = new XSSFWorkbook(file.getInputStream())
//            def sheet = workbook.getSheetAt(0)
//
//            for (cell in sheet.getRow(0).cellIterator()) {
//                sheetheader << cell.stringCellValue
//            }
//
//            def headerFlag = true
//            for (row in sheet.rowIterator()) {
//                if (headerFlag) {
//                    headerFlag = false
//                    continue
//                }
//                def value = ''
//                def map = [:]
//                for (cell in row.cellIterator()) {
//                    switch (cell.cellType) {
//                        case 1:
//                            value = cell.stringCellValue
//                            map["${sheetheader[cell.columnIndex]}"] = value
//                            break
//                        case 0:
//                            value = cell.numericCellValue
//                            map["${sheetheader[cell.columnIndex]}"] = value
//                            break
//                        default:
//                            value = ''
//                    }
//                }
//                values.add(map)
//            }
//
////            values.each { v ->
////                if(v) {
////                    Message.findByMessageRecipient(v.email)?: new Message(email:v.email,fullname:v.fullname).save flush:true, failOnError:true
////                }
//            flash.message = "Subscriber imported successfully"
//            [messageList: values]
//            redirect action: "index"
//        } else
//            flash.message = "no file selected"
//        redirect action: "index"
        def file = request.getFile('file')

        def messageMapList = []
//        def mList = []
        if (file && !file.empty) {
            def newFile = File.createTempFile('grails', 'paymentFile')
            file.transferTo(newFile)
            def importer = new ExcelMessageImporter(newFile)
            messageMapList = importer.list()
            messageMapList.each { data ->
                Message message = new Message(messageRecipient: data.get("messageRecipient"),
                        messageParam1: data.get("param1"), messageParam2: data.get("param2"),
                        messageParam3: data.get("param3"), messageParam4: data.get("param4"))
                System.out.println("" + message)
                messageList.add(message)
            }
//            params.put(messageList: mList)

            redirect(action: 'index')
        }


    }

    def startEmailSending() {
        for (message in messageList) {
            def map = [to: message.messageRecipient, subject: "Email subject", body: message.messageParam4]
            messagingService.sendEmail(map)
            sleep(5000)
        }
        redirect(action: 'index')

    }


}
