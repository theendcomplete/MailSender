package com.theendcomplete.MailSender

import org.grails.plugins.excelimport.AbstractExcelImporter
import org.grails.plugins.excelimport.DefaultImportCellCollector
import org.grails.plugins.excelimport.ExcelImportService
import org.springframework.beans.factory.annotation.Autowired

import static org.grails.plugins.excelimport.ExpectedPropertyType.EmailType
import static org.grails.plugins.excelimport.ExpectedPropertyType.StringType

class ExcelMessageImporter extends AbstractExcelImporter {

    static cellReporter = new DefaultImportCellCollector()

    @Autowired
    ExcelImportService excelImportService

    public ExcelMessageImporter(file) {
        super(file)
        excelImportService = ExcelImportService.getService()
    }

    static Map configurationMap = [
            messageRecipient: ([expectedType: EmailType, defaultValue: ""]),
            param1          : ([expectedType: StringType, defaultValue: ""]),
            param2          : ([expectedType: StringType, defaultValue: ""]),
            param3          : ([expectedType: StringType, defaultValue: ""]),
            param4          : ([expectedType: StringType, defaultValue: ""])
    ]

    static Map CONFIG_MESSAGES_COLUMN_MAP = [
            sheet    : 'Лист1',
            startRow : 1,
            columnMap: [
                    'A': 'messageRecipient',
                    'B': 'param1',
                    'C': 'param2',
                    'D': 'param3',
                    'E': 'param4',
            ]
    ]
//    static Map CONFIG_PAYMENT_COLUMN_MAP = [
//            sheet    : 'Sheet1',
//            startRow : 1,
//            columnMap: [
//                    'A': 'code',
//                    'B': 'id',
//            ]
//    ]

    List<Map> list() {
        excelImportService.columns(
                workbook,
                CONFIG_MESSAGES_COLUMN_MAP,
                cellReporter,
                configurationMap
        )
    }


}
