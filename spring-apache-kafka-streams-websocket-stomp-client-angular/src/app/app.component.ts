import { OnInit, Component } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

//import Stomp from 'stompjs';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

//import $ from 'jquery';
declare const $: any;

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  url = 'http://localhost:8080/websocket'
  client: any;
  
  ngOnInit() {
	this.title.setTitle('Angular Spring Websocket');
  }
  
  constructor(private title: Title){
    this.connection();
  }
  
  connection() {
//debugger; 
	const client = new Client({
	  // For SockJS, set a factory that creates a new SockJS instance
	  webSocketFactory: () => new SockJS('http://localhost:8080/websocket'),
	  
	  //brokerURL: 'ws://localhost:8080/websocket', // Native WebSocket URL (if available)
	  // connectHeaders: {
		// login: 'user',
		// passcode: 'password',
	  // },
	  debug: function (str) {
		console.log(str);
	  },
	  reconnectDelay: 5000,
	  heartbeatIncoming: 4000,
	  heartbeatOutgoing: 4000,
	});


	// Fallback code
	if (typeof WebSocket !== 'function') {
	  // For SockJS, set a factory that creates a new SockJS instance
	  // to be used for each (re)connect
	  client.webSocketFactory = () => new SockJS('http://localhost:8080/websocket');
	  // client.webSocketFactory = function () {
		// return new SockJS('http://localhost:8080/websocket');
	  // };
	}

	client.onConnect = function (frame) {
	  client.subscribe("/topic/greeting", (message: any) => {
		console.log(`Received: ${message.body}`)
		if(message.body) {
		  $(".msg").html(message.body)
		}
	  });
    };

	client.onStompError = function (frame) {
	  // Invoked in case of an error reported by the broker
	  // Bad login/passcode typically causes an error
	  // Compliant brokers set the `message` header with a brief message; the body may contain details.
	  // Compliant brokers terminate the connection after any error
	  console.log('Broker reported error: ' + frame.headers['message']);
	  console.log('Additional details: ' + frame.body);
	};

	client.activate();  
  
  
  
  }
  
  // connection(){
	// const client = new Client({
	  // brokerURL: 'ws://localhost:8080/websocket',
	  // onConnect: () => {
		// client.subscribe("/topic/greeting", (message: any) => {
		  // console.log(`Received: ${message.body}`)
		  // if(message.body) {
			// $(".msg").html(message.body)
		  // }
		// });		  
		// client.publish({ destination: '/topic/test01', body: 'First Message' });
	  // },
	  // onDisconnect: () => {
		// console.log('Disconnected');
	  // },	  
	// });

	// client.activate();	
	  
  // }  

  // connection(){
    // let ws = new SockJS(this.url);
    // this.client = Stomp.over(ws);
    // let that: any = this;
	
    // this.client.connect({}, function(frame: any) {
      // that.client.subscribe("/topic/greeting", (message: any) => {
	    // console.log(`Received: ${message.body}`)
        // if(message.body) {
		  // $(".msg").html(message.body)
        // }
      // });
    // });	  
  // }
}
