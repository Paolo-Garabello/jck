<script lang="ts">
  import SquareSection from '$lib/components/SquareSection.svelte';

  let logged: boolean = localStorage.getItem('logged') == 'true';
</script>

<header>
  <h1>Welcome to JCK</h1>
  <h2>The Java-based Chat Kit</h2>
</header>

<main>
  <SquareSection
    title="Public Square"
    description="Chat with anyone, anonymously"
    link="/public-square"
  />

  <SquareSection
    title="Private Chats"
    description="Encrypted chats with your friends"
    link="/dm"
    blocker= {{
      enabled: !logged,
      displayString: "Login to Access!",
      url: "/login"
    }}
  />
</main>

<style lang="scss">
  header {
    text-align: center;
    margin-bottom: 10%;

    h1 {
      font-size: clamp(20px, 8vw, 70px);
    }

    h2 {
      font-size: clamp(5px, 4vw, 40px);
    }
  }

  main {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    flex-wrap: wrap;
    gap: 10%;
  }
</style>
