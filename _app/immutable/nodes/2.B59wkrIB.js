import{s as R,n as I}from"../chunks/scheduler.DbuCXXLn.js";import{S as V,i as W,e as b,f as w,s as q,g as k,h as E,j as A,k as f,l as C,q as _,B as T,n as y,o as m,p as L,c as M,r as z,a as D,m as J,t as K,b as N,d as j}from"../chunks/index.TDUkrpct.js";function B(r){let e,l=r[3].displayString+"",s;return{c(){e=b("p"),s=w(l),this.h()},l(t){e=k(t,"P",{class:!0});var a=E(e);s=A(a,l),a.forEach(f),this.h()},h(){_(e,"class","blocker svelte-iheuhk")},m(t,a){y(t,e,a),m(e,s)},p(t,a){a&8&&l!==(l=t[3].displayString+"")&&L(s,l)},d(t){t&&f(e)}}}function F(r){var P;let e,l,s,t,a,d,n,g,i,h,o=((P=r[3])==null?void 0:P.enabled)&&B(r);return{c(){e=b("a"),l=b("section"),s=b("div"),t=b("h2"),a=w(r[0]),d=q(),n=b("p"),g=w(r[1]),i=q(),o&&o.c(),this.h()},l(c){e=k(c,"A",{href:!0,class:!0});var u=E(e);l=k(u,"SECTION",{class:!0});var p=E(l);s=k(p,"DIV",{class:!0});var v=E(s);t=k(v,"H2",{class:!0});var S=E(t);a=A(S,r[0]),S.forEach(f),d=C(v),n=k(v,"P",{});var H=E(n);g=A(H,r[1]),H.forEach(f),v.forEach(f),i=C(p),o&&o.l(p),p.forEach(f),u.forEach(f),this.h()},h(){var c,u;_(t,"class","svelte-iheuhk"),_(s,"class","svelte-iheuhk"),T(s,"blur",(c=r[3])==null?void 0:c.enabled),_(l,"class","svelte-iheuhk"),_(e,"href",h=(u=r[3])!=null&&u.enabled?r[3].url:r[2]),_(e,"class","wrapper svelte-iheuhk")},m(c,u){y(c,e,u),m(e,l),m(l,s),m(s,t),m(t,a),m(s,d),m(s,n),m(n,g),m(l,i),o&&o.m(l,null)},p(c,[u]){var p,v,S;u&1&&L(a,c[0]),u&2&&L(g,c[1]),u&8&&T(s,"blur",(p=c[3])==null?void 0:p.enabled),(v=c[3])!=null&&v.enabled?o?o.p(c,u):(o=B(c),o.c(),o.m(l,null)):o&&(o.d(1),o=null),u&12&&h!==(h=(S=c[3])!=null&&S.enabled?c[3].url:c[2])&&_(e,"href",h)},i:I,o:I,d(c){c&&f(e),o&&o.d()}}}function G(r,e,l){let{title:s}=e,{description:t}=e,{link:a}=e,{blocker:d=null}=e;return r.$$set=n=>{"title"in n&&l(0,s=n.title),"description"in n&&l(1,t=n.description),"link"in n&&l(2,a=n.link),"blocker"in n&&l(3,d=n.blocker)},[s,t,a,d]}class O extends V{constructor(e){super(),W(this,e,G,F,R,{title:0,description:1,link:2,blocker:3})}}function Q(r){let e,l='<h1 class="svelte-1gi98h9">Welcome to JCK</h1> <h2 class="svelte-1gi98h9">The Java-based Chat Kit</h2>',s,t,a,d,n,g;return a=new O({props:{title:"Public Square",description:"Chat with anyone, anonymously",link:"/public-square"}}),n=new O({props:{title:"Private Chats",description:"Encrypted chats with your friends",link:"/dm",blocker:{enabled:!r[0],displayString:"Login to Access!",url:"/login"}}}),{c(){e=b("header"),e.innerHTML=l,s=q(),t=b("main"),M(a.$$.fragment),d=q(),M(n.$$.fragment),this.h()},l(i){e=k(i,"HEADER",{class:!0,"data-svelte-h":!0}),z(e)!=="svelte-1gei2hy"&&(e.innerHTML=l),s=C(i),t=k(i,"MAIN",{class:!0});var h=E(t);D(a.$$.fragment,h),d=C(h),D(n.$$.fragment,h),h.forEach(f),this.h()},h(){_(e,"class","svelte-1gi98h9"),_(t,"class","svelte-1gi98h9")},m(i,h){y(i,e,h),y(i,s,h),y(i,t,h),J(a,t,null),m(t,d),J(n,t,null),g=!0},p(i,[h]){const o={};h&1&&(o.blocker={enabled:!i[0],displayString:"Login to Access!",url:"/login"}),n.$set(o)},i(i){g||(K(a.$$.fragment,i),K(n.$$.fragment,i),g=!0)},o(i){N(a.$$.fragment,i),N(n.$$.fragment,i),g=!1},d(i){i&&(f(e),f(s),f(t)),j(a),j(n)}}}function U(r,e,l){let{data:s}=e,t=localStorage.getItem("logged")=="true";return t||s.websocket.addEventListener("message",a=>{a.data.statusCode===205&&a.data.ok&&l(0,t=!0)}),r.$$set=a=>{"data"in a&&l(1,s=a.data)},[t,s]}class Z extends V{constructor(e){super(),W(this,e,U,Q,R,{data:1})}}export{Z as component};
