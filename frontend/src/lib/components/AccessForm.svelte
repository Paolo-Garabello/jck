<script lang="ts">
  import { goto } from '$app/navigation';
  import { getNotificationsContext } from 'svelte-notifications';

  export let requestType: string;
  export let goToUrl: string;
  export let goToText: string;
  export let websocket: WebSocket;

  let username: string = "";
  let password: string = "";

  sessionStorage.setItem('expected.message', requestType);

  let upperRequestType = requestType.charAt(0).toUpperCase() + requestType.slice(1);

  const { addNotification } = getNotificationsContext();

  websocket.addEventListener('message', () => {
    let msg = sessionStorage.getItem('websocket.message');
    let req = sessionStorage.getItem('expected.message');

    if(!msg || !req) return;

    const data = JSON.parse(msg);

    if(req === 'signup') {
      if(data.ok) {

        if(data.statusCode === 201) {
          sessionStorage.removeItem('websocket.message');

          addNotification({
            text: "Signup Successfull",
            type: 'success',
            position: 'bottom-right',
            removeAfter: 4000
          });
        }

      } else {

        if(data.statusCode === 409) {
          sessionStorage.removeItem('websocket.message');

          addNotification({
            text: data.message,
            type: 'error',
            position: 'bottom-right',
            removeAfter: 4000
          });
        }

      }
    }

    if(req === 'login') {
      if(data.ok) {

        if(data.statusCode === 204) {
          localStorage.setItem('logged', 'true');
          sessionStorage.removeItem('websocket.message');
          goto('/dm');
        }

      } else {

        if(data.statusCode === 401) {
          sessionStorage.removeItem('websocket.message');

          addNotification({
            text: data.message,
            type: 'error',
            position: 'bottom-right',
            removeAfter: 4000
          });
        }

      }

    }

  });

  function handleSubmit(event: Event) {
    event.preventDefault();
    websocket.send(JSON.stringify({
      request: requestType,
      userInfo: {
        username: username,
        password: password
      }
    }));

    username = '';
    password = '';
  }
</script>

<main>
  <form on:submit={handleSubmit}>
    <h1>{upperRequestType}</h1>

    <label for="username">Username</label>
    <input bind:value={username} type="text" placeholder="Username" id="username" required>

    <label for="password">Password</label>
    <input bind:value={password} type="password" placeholder="Password" id="password" required>

    <button type="submit">{upperRequestType}</button>

    <a href="{goToUrl}">{goToText}</a>
  </form>
</main>

<style lang="scss">
  main {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;

    form {
      box-sizing: border-box;
      height: 520px;
      width: calc(100% - 20vw);
      max-width: 400px;
      background-color: rgba(255,255,255,0.13);
      border-radius: 10px;
      backdrop-filter: blur(10px);
      border: 2px solid rgba(255,255,255,0.1);
      box-shadow: 0 0 40px rgba(8,7,16,0.6);
      padding: 50px 35px;

      * {
        font-family: 'Poppins',sans-serif;
        color: #ffffff;
        letter-spacing: 0.5px;
        outline: none;
        border: none;
      }

      h1 {
        font-size: 32px;
        font-weight: 500;
        line-height: 42px;
        text-align: center;
      }

      label {
        display: block;
        margin-top: 30px;
        font-size: 16px;
        font-weight: 500;
      }

      input{
        display: block;
        height: 50px;
        width: 100%;
        background-color: rgba(255,255,255,0.07);
        border-radius: 3px;
        padding: 0 10px;
        margin-top: 8px;
        font-size: 14px;
        font-weight: 300;
      }

      ::placeholder{
        color: #e5e5e5;
      }

      button{
        margin: 50px 0 10px 0;
        width: 100%;
        background-color: #ffffff;
        color: #080710;
        padding: 15px 0;
        font-size: 18px;
        font-weight: 600;
        border-radius: 5px;
        cursor: pointer;
      }
    }
  }

</style>