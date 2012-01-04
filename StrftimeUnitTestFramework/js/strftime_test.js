/**
 * Created by JetBrains WebStorm.
 * User: mike
 * Date: 12/01/03
 * Time: 1:46
 * To change this template use File | Settings | File Templates.
 */

var date = new Date(2009, 9, 5);
try {
    assert('%Y should return full year', date.strftime("%Y") === '2009');
    assert('%m should return month', date.strftime("%m") === '10');
    assert('%d should return date', date.strftime('%d') === '05');
    assert('%y should return year as two digits', date.strftime('%y') === '09')
    assert('%F should return act as %Y-%m-%d', date.strftime('%F') === '2009-10-05');

    showSingleResult(assert.count + 'tests OK', Result.succeed);
} catch (e) {
    showSingleResult('Test failed: ' + e.message, Result.failure);
}

