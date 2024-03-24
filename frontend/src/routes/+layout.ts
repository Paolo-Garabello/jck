import { PUBLIC_WEBSOCKET_URL } from '$env/static/public';
import type { WebSocketResponse } from '$lib/types/WebSocketResponse.js';

export const ssr = false;

export const load = ({ params }) => {
  let websocket: WebSocket = new WebSocket(PUBLIC_WEBSOCKET_URL);
  let token: string|null = localStorage.getItem('auth_token');

  websocket.onopen = () => {
    console.log('WebSocket connection established');

    websocket.send(JSON.stringify({
      'request': 'auth',
      'data': token,
    }));
  }

  websocket.onclose = () => {
    console.log('WebSocket connection closed.');
  }

  websocket.onerror = (error) => {
    console.error('WebSocket error:', error);
  };


  websocket.onmessage = (event: MessageEvent) => {
    sessionStorage.setItem('websocket.message', event.data);
    let msg = sessionStorage.getItem('websocket.message');

    if(!msg) return;

    const wsdata: WebSocketResponse = JSON.parse(msg);
    console.log(wsdata);

    if(wsdata.statusCode === 205) { // Expecting a token from the server or wrong token was sent
      localStorage.setItem('auth_token', wsdata.data.token);
      sessionStorage.setItem('public_username', wsdata.data.publicUsername);
      sessionStorage.removeItem('websocket.message');
    }

  }

  return {
    websocket: websocket
  }
}
