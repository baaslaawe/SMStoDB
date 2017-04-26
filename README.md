# SMS To DB

Push SMS to database if found any word. Feel free to use this code.

## Library Usage

Library|Purpose|Link
---|---|---
Android Volley | HTTP Connection | https://github.com/google/volley
Apache Common Lang | `StringUtils` class | https://mvnrepository.com/artifact/org.apache.commons/commons-lang3


## Database

### Structure

*#* | Name | Type | Null | Extra
--- | --- | --- |--- | --- 
1 | id | int(11) | No | AUTO_INCREMENT |
2 | sender | varchar(20) | No | - |
3 | message | varchar(500) | No | - |
4 | bank_name | varchar(20) | No | - |
5 | date_added | datetime | No | - |

```
CREATE TABLE IF NOT EXISTS `message` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `sender` varchar(20) NOT NULL,
    `message` varchar(500) NOT NULL,
    `bank_name` varchar(20) NOT NULL,
    `date_added` datetime NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
```

## API

Examples to implement at server side can be found at:
> SMSToDB > API 

Method | Method Name | Parameter
--- | --- | ---
POST | save-sms.php | `sender`: Sender phone number<br>`message`: SMS content<br>`bank name`: Message from which bank<br>`date_added`: Date message was received

