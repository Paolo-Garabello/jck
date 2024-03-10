import { PUBLIC_WEBSOCKET_URL } from '$env/static/public';

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

    const data = JSON.parse(msg);

    if(data.username) {
      sessionStorage.setItem('public_username', data.username);
      sessionStorage.removeItem('websocket.message');
    }

    if(data.statusCode === 205) { // Expecting a token from the server or wrong token was sent
      localStorage.setItem('auth_token', data.message);
      sessionStorage.removeItem('websocket.message');
    }

  }

  return {
    websocket: websocket
  }
}
