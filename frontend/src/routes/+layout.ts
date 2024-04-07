import { goto } from '$app/navigation';
import { PUBLIC_WEBSOCKET_URL } from '$env/static/public';
import { heehee } from '$lib/helpers/heehee';
import type { WebSocketResponse } from '$lib/types/WebSocketResponse.js';

export const ssr = false;

export const load = () => {
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
    console.log("ws data: " + JSON.stringify(wsdata));

    if(wsdata.statusCode === 205) { // Expecting a token from the server or wrong token was sent
      localStorage.setItem('logged', String(wsdata.ok));
      localStorage.setItem('auth_token', wsdata.data.token);
      sessionStorage.setItem('public_username', wsdata.data.publicUsername);
      sessionStorage.removeItem('websocket.message');
    }

    if(wsdata.statusCode === 403) {
      localStorage.setItem('logged', 'false');
      localStorage.setItem('dmMessages', '[]');
      localStorage.setItem('user', '{}');
      goto('/login');
    }

    if(wsdata.statusCode === 202) {
      if(!document.hasFocus()) {
        heehee();
      }
    }

  }

  return {
    websocket: websocket
  }
}
