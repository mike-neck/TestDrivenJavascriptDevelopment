/**
 * Created by JetBrains WebStorm.
 * User: mike
 * Date: 12/01/03
 * Time: 1:46
 * To change this template use File | Settings | File Templates.
 */

var date = new Date(2009, 9, 5);
testCase(
    "strftime test", {
        "test format specifier %Y" : function() {
            assert('%Y should return full year', date.strftime('%Y') === '2009');
        },
        "test format specifier %m" : function() {
            assert('%m should return month', date.strftime('%m') === '10');
        },
        "test format specifier %d" : function() {
            assert('%d should return date', date.strftime('%d') === '05');
        },
        "test format specifier %y" : function() {
            assert('%y should return year as two digits', date.strftime('%y') === '09');
        },
        "test format specifier %F" : function() {
            assert('%F should act as %Y-%m-%d', date.strftime('%F') === '2009-10-05');
        }
    }
);
