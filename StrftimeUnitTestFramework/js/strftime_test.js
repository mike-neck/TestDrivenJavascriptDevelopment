/**
 * Created by JetBrains WebStorm.
 * User: mike
 * Date: 12/01/03
 * Time: 1:46
 * To change this template use File | Settings | File Templates.
 */

testCase(
    "strftime test", {
        "setUp" : function() {
            this.date = new Date(2009, 9, 5);
        },
        "test format specifier %Y" : function() {
            assert('%Y should return full year', this.date.strftime('%Y') === '2009');
        },
        "test format specifier %m" : function() {
            assert('%m should return month', this.date.strftime('%m') === '10');
        },
        "test format specifier %d" : function() {
            assert('%d should return date', this.date.strftime('%d') === '05');
        },
        "test format specifier %y" : function() {
            assert('%y should return year as two digits', this.date.strftime('%y') === '09');
        },
        "test format specifier %F" : function() {
            assert('%F should be shortcut for %Y-%m-%d', Date.formats.F === '%Y-%m-%d');
        }
    }
);
