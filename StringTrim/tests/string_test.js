/**
 * Created by JetBrains WebStorm.
 * User: mike
 * Date: 12/01/13
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
testCase(
    "String trim test", {
        "test trim should remove leading white-space" : function() {
            assert(
                "trim() should remove leading white-space",
                "a string" === "  a string".trim()
            );
        },
        "test trim should remove trailing white-space" : function () {
            assert(
                "trim() should remove trailing white-space",
                "a string" === "a string    ".trim()
            );
        }
    }
);