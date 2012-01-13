class ConstantPath {
    static final String serverPath = 'C:/Users/mike/repo/TestDrivenJavascriptDevelopment/StringTrim/'
    static final String serverName = 'StringTrim'
    static final String rootFile = 'string_trim.html'
}

@Grab('org.eclipse.jetty:jetty-server:7.5.4.v20111024')
@Grab('commons-logging:commons-logging:1.1.1')

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.AbstractHandler
import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import groovy.xml.MarkupBuilder
import groovy.io.FileType

def inet = new InetSocketAddress('localhost', 3309)
def server = new Server(inet)

class JsUnitTestServer extends AbstractHandler {

    def definePath = {
        def html = new File(ConstantPath.getRootFile())
        def path = html.getAbsolutePath().replace(ConstantPath.getRootFile(), '')
        if (path.contains(ConstantPath.getServerName()) == false) {
            path = ConstantPath.getServerPath()
        }
        path
    }

    def jsEntry = {
        def dir = ['js', 'tests']

        def jsFiles = [:]

        dir.each {
            def jsDir = "${definePath()}${it}".toString()
            new File(jsDir).eachFile(FileType.FILES) {
                jsFiles["/${it.getName()}".toString()] = it
            }
        }
        jsFiles
    }

    def js = [:]

    JsUnitTestServer() {
        super()
        js = jsEntry()
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
            w << '<!DOCTYPE html>\n'
            def doc = new MarkupBuilder(w)
            doc.html(lang : 'ja') {
                head {
                    title('string trim test')
                    meta('http-equiv' : 'Content-Type', content : 'text/html;cahrset=utf-8')
                }
                body {
                    js.each {k, v ->
                        script(type : 'text/javascript', src : "${k}", '' )
                    }
                }
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
                    p {
                        a(href : 'http://localhost:3309', 'go to test page')
                    }
                }
            }
            res.writer << w.toString()
            res.writer.flush()
            server.stop()
            server.destroy()
        } else if (js.containsKey("${target}".toString())) {
            println "found ${target}"
            res.setStatus(200)
            res.setContentType('text/javascript')
            def file = js[target]
            file.eachLine {
                res.writer << "${it}\n"
            }
            res.writer.flush()
        } else {
            println "not found ${target}"
            res.setStatus(404)
        }
    }
}

def handler = new JsUnitTestServer()
server.setHandler(handler)
server.start()