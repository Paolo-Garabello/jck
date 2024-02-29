<script lang="ts">
  export let websocket: WebSocket;

  websocket.addEventListener('message', () => {

  });

  if(websocket.readyState == WebSocket.OPEN) {
    websocket.send(JSON.stringify({
      request: 'getChats'
    }));
    
  } else {
    websocket.addEventListener('open', () => {
      websocket.send(JSON.stringify({
        request: 'getChats'
      }));
    });
  }

</script>


<h1>Private Chats</h1>
