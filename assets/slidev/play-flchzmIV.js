function __vite__mapDeps(indexes) {
  if (!__vite__mapDeps.viteFileDeps) {
    __vite__mapDeps.viteFileDeps = ["assets/slidev/DrawingControls-BNLwBv-G.js","assets/modules/unplugin-icons-D4R0xC7_.js","assets/modules/vue-BNkoxSqV.js","assets/modules/shiki-B7STecTN.js","assets/modules/shiki-BSchMNmt.css","assets/slidev/DrawingPreview-CEgdQDnr.js","assets/index-oshRG3ZJ.js","assets/index-BoJ7CY0k.css","assets/slidev/bottom-DLN0hcYL.js","assets/bottom-D_wCXcB8.css","assets/slidev/SlidesShow-BmyPnOW5.js","assets/slidev/context-B5J5u7v4.js","assets/SlidesShow-CaYKKxQ8.css","assets/DrawingControls-C5T1oZL5.css"]
  }
  return indexes.map((i) => __vite__mapDeps.viteFileDeps[i])
}
import{b as y,c as k,u as I,_ as $}from"../index-oshRG3ZJ.js";import{d as h,a6 as N,o as s,c as r,A as e,b as f,e as l,f as z,i as j,g as a,a7 as B,y as P,k as D,a5 as g,a0 as O,M as w,l as v,F as E,t as R,h as A}from"../modules/vue-BNkoxSqV.js";import{b as x,e as C,f as S,h as T,j as M,w as U,k as H,l as L}from"./bottom-DLN0hcYL.js";import{Q as F,G,r as Q,u as W,S as K,a as X,N as Y}from"./SlidesShow-BmyPnOW5.js";import{P as q}from"./PrintStyle-C0dtrSqv.js";import{u as J}from"./DrawingPreview-CEgdQDnr.js";import"../modules/shiki-B7STecTN.js";import"../modules/unplugin-icons-D4R0xC7_.js";import"./context-B5J5u7v4.js";const Z="/jck/assets/logo-BYkHSa_O.png",ee={key:0,class:"fixed top-0 bottom-0 left-0 right-0 grid z-20"},oe=h({__name:"Modal",props:{modelValue:{default:!1},class:{default:""}},emits:["update:modelValue"],setup(u,{emit:n}){const i=u,o=N(i,"modelValue",n);function t(){o.value=!1}return(c,d)=>(s(),r(B,null,[e(o)?(s(),f("div",ee,[l("div",{bg:"black opacity-80",class:"absolute top-0 bottom-0 left-0 right-0 -z-1",onClick:d[0]||(d[0]=m=>t())}),l("div",{class:j(["m-auto rounded-md bg-main shadow",i.class]),"dark:border":"~ main"},[z(c.$slots,"default")],2)])):a("v-if",!0)],1024))}}),se=y(oe,[["__file","/home/runner/work/jck/jck/presentation/node_modules/.pnpm/@slidev+client@0.48.8_postcss@8.4.38_vite@5.2.8/node_modules/@slidev/client/internals/Modal.vue"]]),te={class:"slidev-info-dialog slidev-layout flex flex-col gap-4 text-base"},le=["innerHTML"],ae=l("a",{href:"https://github.com/slidevjs/slidev",target:"_blank",class:"!opacity-100 !border-none !text-current"},[l("div",{class:"flex gap-1 children:my-auto"},[l("div",{class:"opacity-50 text-sm mr-2"},"Powered by"),l("img",{class:"w-5 h-5",src:Z,alt:"Slidev logo"}),l("div",{style:{color:"#2082A6"}},[l("b",null,"Sli"),O("dev ")])])],-1),ne=h({__name:"InfoDialog",props:{modelValue:{default:!1}},emits:["update:modelValue"],setup(u,{emit:n}){const o=N(u,"modelValue",n),t=P(()=>typeof k.info=="string");return(c,d)=>(s(),r(se,{modelValue:e(o),"onUpdate:modelValue":d[0]||(d[0]=m=>g(o)?o.value=m:null),class:"px-6 py-4"},{default:D(()=>[l("div",te,[t.value?(s(),f("div",{key:0,class:"mb-4",innerHTML:e(k).info},null,8,le)):a("v-if",!0),ae])]),_:1},8,["modelValue"]))}}),ie=y(ne,[["__file","/home/runner/work/jck/jck/presentation/node_modules/.pnpm/@slidev+client@0.48.8_postcss@8.4.38_vite@5.2.8/node_modules/@slidev/client/internals/InfoDialog.vue"]]),re=h({__name:"Controls",setup(u){const n=w(),i=w();return(_,o)=>(s(),f(E,null,[v(F,{modelValue:e(x),"onUpdate:modelValue":o[0]||(o[0]=t=>g(x)?x.value=t:null)},null,8,["modelValue"]),v(G),n.value?(s(),r(e(n),{key:0})):a("v-if",!0),i.value?(s(),r(e(i),{key:1,modelValue:e(C),"onUpdate:modelValue":o[1]||(o[1]=t=>g(C)?C.value=t:null)},null,8,["modelValue"])):a("v-if",!0),e(k).info?(s(),r(ie,{key:2,modelValue:e(S),"onUpdate:modelValue":o[2]||(o[2]=t=>g(S)?S.value=t:null)},null,8,["modelValue"])):a("v-if",!0)],64))}}),de=y(re,[["__file","/home/runner/work/jck/jck/presentation/node_modules/.pnpm/@slidev+client@0.48.8_postcss@8.4.38_vite@5.2.8/node_modules/@slidev/client/internals/Controls.vue"]]),ue=h({__name:"play",setup(u){Q();const{next:n,prev:i,isEmbedded:_,isPrintMode:o}=I(),{isDrawing:t}=J(),c=R();function d(p){var b;M.value||((b=p.target)==null?void 0:b.id)==="slide-container"&&(p.screenX/window.innerWidth>.6?n():i())}W(c);const m=P(()=>T.value||M.value);w();const V=w();return $(()=>import("./DrawingControls-BNLwBv-G.js"),__vite__mapDeps([0,1,2,3,4,5,6,7,8,9,10,11,12,13])).then(p=>V.value=p.default),(p,b)=>(s(),f(E,null,[e(o)?(s(),r(q,{key:0})):a("v-if",!0),l("div",{id:"page-root",ref_key:"root",ref:c,class:j(["grid",e(L)?"grid-rows-[1fr_max-content]":"grid-cols-[1fr_max-content]"])},[v(K,{class:"w-full h-full",style:A({background:"var(--slidev-slide-container-background, black)"}),width:e(o)?e(U).width.value:void 0,scale:e(H),"is-main":!0,onPointerdown:d},{default:D(()=>[v(X,{"render-context":"slide"})]),controls:D(()=>[e(o)?a("v-if",!0):(s(),f("div",{key:0,class:j(["absolute bottom-0 left-0 transition duration-300 opacity-0 hover:opacity-100",[m.value?"!opacity-100 right-0":"opacity-0 p-2",e(t)?"pointer-events-none":""]])},[v(Y,{class:"m-auto",persist:m.value},null,8,["persist"])],2)),!e(k).drawings.presenterOnly&&!e(_)&&V.value?(s(),r(e(V),{key:1,class:"ml-0"})):a("v-if",!0)]),_:1},8,["style","width","scale"]),a("v-if",!0)],2),e(o)?a("v-if",!0):(s(),r(de,{key:1}))],64))}}),ye=y(ue,[["__file","/home/runner/work/jck/jck/presentation/node_modules/.pnpm/@slidev+client@0.48.8_postcss@8.4.38_vite@5.2.8/node_modules/@slidev/client/pages/play.vue"]]);export{ye as default};
