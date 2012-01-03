/**
 * Created by JetBrains WebStorm.
 * User: mike
 * Date: 12/01/04
 * Time: 2:47
 * To change this template use File | Settings | File Templates.
 */

function assert (message, expr) {
    if (!expr) {
        throw new Error(message);
    }
    assert.count++;
    return true;
}

assert.count = 0;
