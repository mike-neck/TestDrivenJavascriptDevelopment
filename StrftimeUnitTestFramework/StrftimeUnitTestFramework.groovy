class ConstantPath {
    static final String serverPath = 'C:/Users/mike/repo/TestDrivenJavascriptDevelopment/StrftimeUnitTestFramework/'
}

@Grab('org.eclipse.jetty:jetty-server:7.5.4.v20111024')
@Grab('commons-logging:commons-logging:1.1.1')

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.AbstractHandler
import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import groovy.xml.MarkupBuilder

def inet = new InetSocketAddress('localhost', 3309)
def server = new Server(inet)

class JsUnitTestServer extends AbstractHandler {

    def definePath = {
        def path = new File('strftime.html').getAbsolutePath().replace('strftime.html', '')
        if (path.contains('StrftimeUnitTestFramework') == false) {
            path = ConstantPath.getServerPath()
        }
        path
    }
    
    def path

    def jsFiles = [
        'js/strftime.js',
        'js/strftime_test.js',
        'js/assertion.js'
    ]

    def js = [:]

    JsUnitTestServer() {
        super()
        this.path = definePath()
        jsFiles.each {
            def req = "/${it}".toString()
            js[req] = new File("${path}${it}")
        }
        println 'js prepared'
        js.each {k, v ->
            println "$k : $v"
        }
    }

    @Override
    public void handle(
            String target,
            Request baseRequest,
            HttpServletRequest req,
            HttpServletResponse res) {
        println target
        if('/'.equals(target)) {
            res.setStatus(200)
            res.setContentType('text/html')
            def w = res.writer
            new File("${path}strftime.html").eachLine {
                w << it
                w << '\n'
            }
            w.flush()
        } else if ('/end'.equals(target)) {
            res.setStatus(200)
            res.setContentType('text/html')
            def w = new StringWriter()
            w << '<!DOCTYPE html>\n'
            def doc = new MarkupBuilder(w)
            doc.html(lang : 'ja') {
                head() {
                    title('strftime test finish')
                    meta('http-equiv' : 'Content-Type', content : 'text/html;charset=utf-8')
                }
                body() {
                    h1('server finished')
                }
            }
            res.writer << w.toString()
            res.writer.flush()
            server.stop()
            server.destroy()
        } else if (js.containsKey(target)) {
            res.setStatus(200)
            res.setContentType('text/javascript')
            def file = js[target]
            file.eachLine {
                res.writer << "${it}\n"
            }
            res.writer.flush()
        } else {
            res.setStatus(404)
        }
    }
}

def handler = new JsUnitTestServer()
server.setHandler(handler)
server.start()