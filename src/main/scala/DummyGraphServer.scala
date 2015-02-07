package com.github.pankajgupta.DummyGraphServer

import DummyGraphService._
import com.twitter.cassovary.graph.TestGraphs
import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}

object Server {

def main(args: Array[String]) {
val graph = TestGraphs.g6
val server = Thrift.serveIface("localhost:8080", new DummyGraphService[Future] {
  def numNodes() = Future.value(graph.nodeCount)
})
val client = Thrift.newIface[DummyGraphService.FutureIface]("localhost:8080")
client.numNodes() onSuccess { n => println(s"Looks like the graph on the server has $n nodes") }
Await.ready(server)
}

}

