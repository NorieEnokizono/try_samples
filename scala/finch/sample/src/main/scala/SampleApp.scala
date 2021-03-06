
import io.finch._
import io.finch.syntax._
import com.twitter.finagle.Http
import com.twitter.util.Await

object SampleApp extends App {

	// "/samples"
	val samples = get("samples") { Ok("samples") }

	// "/samples/xxx"
	val singleSample = get("samples" :: path[String]) { s: String =>
		Ok(s"sample: $s")
	}

	// "/users/xxx?name=xxx"
	val paramUser = get("users" :: path[String] :: param("name")) { (s: String, n: String) =>
		Ok(s"user: $s, $n")
	}

	// "/a/notes/xxx or /b/notes/xxx"
	val choiceNotes = get( ("a" coproduct "b") :: "notes" :: path[Int]) { s: Int =>
		Ok(s"notes: $s")
	}

	val api = samples :+: singleSample :+: paramUser :+: choiceNotes

	val server = Http.server.serve(":8080", api.toServiceAs[Text.Plain])

	Await.ready(server)
}