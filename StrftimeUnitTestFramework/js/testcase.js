/**
 * Created by JetBrains WebStorm.
 * User: mike
 * Date: 12/01/06
 * Time: 0:03
 * To change this template use File | Settings | File Templates.
 */

function testCase (name, tests) {
    assert.count = 0
    var successful = 0,
        testCount = 0,
        test,
        color;

    for (test in tests) {
        if (!/^test/.test(test)) {
            continue;
        }

        testCount += 1;

        try {
            tests[test] ();
            showSingleResult(test, Result.succeed);
            successful += 1;
        } catch (e) {
            showSingleResult(test + ' failed : ' + e.message, Result.failure);
        }
    }

    color = (successful === testCount)? Result.succeed : Result.failure;
    showSingleResult('<span style="font-weight: bold;">' +
        testCount + ' tests, ' +
        (testCount - successful) +
        ' failures</span>', color);
}
