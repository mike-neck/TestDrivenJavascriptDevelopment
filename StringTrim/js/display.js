/**
 * Created by JetBrains WebStorm.
 * User: mike
 * Date: 12/01/05
 * Time: 3:57
 * To change this template use File | Settings | File Templates.
 */

var Result = {
    succeed : '#0c0',
    failure : '#c00',
    message : '#fff'
};

function showSingleResult (message, color) {
    var element = document.createElement('p');
    element.innerHTML = message;
    element.style.backgroundColor = color;
    element.style.color = Result.message;
    document.body.appendChild(element);
}
