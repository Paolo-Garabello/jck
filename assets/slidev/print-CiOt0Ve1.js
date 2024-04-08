import{d,$ as u,y as h,b as a,e as t,x as s,A as r,F as v,Z as f,o as n,a0 as g,l as x,g as b}from"../modules/vue-DW0T5VS7.js";import{u as k,h as y,c as m,b as N}from"../index-cY5iy99q.js";import{N as w}from"./NoteDisplay-CPF7sB2P.js";import"../modules/shiki-j_GYkTMY.js";const L={id:"page-root"},T={class:"m-4"},V={class:"mb-10"},j={class:"text-4xl font-bold mt-2"},B={class:"opacity-50"},D={class:"text-lg"},H={class:"font-bold flex gap-2"},S={class:"opacity-50"},A=t("div",{class:"flex-auto"},null,-1),C={key:0,class:"border-main mb-8"},F=d({__name:"print",setup(M){const{slides:p,total:_}=k();u(`
@page {
  size: A4;
  margin-top: 1.5cm;
  margin-bottom: 1cm;
}
* {
  -webkit-print-color-adjust: exact;
}
html,
html body,
html #app,
html #page-root {
  height: auto;
  overflow: auto !important;
}
`),y({title:`Notes - ${m.title}`});const i=h(()=>p.value.map(o=>{var l;return(l=o.meta)==null?void 0:l.slide}).filter(o=>o!==void 0&&o.noteHTML!==""));return(o,l)=>(n(),a("div",L,[t("div",T,[t("div",V,[t("h1",j,s(r(m).title),1),t("div",B,s(new Date().toLocaleString()),1)]),(n(!0),a(v,null,f(i.value,(e,c)=>(n(),a("div",{key:c,class:"flex flex-col gap-4 break-inside-avoid-page"},[t("div",null,[t("h2",D,[t("div",H,[t("div",S,s(e==null?void 0:e.no)+"/"+s(r(_)),1),g(" "+s(e==null?void 0:e.title)+" ",1),A])]),x(w,{"note-html":e.noteHTML,class:"max-w-full"},null,8,["note-html"])]),c<i.value.length-1?(n(),a("hr",C)):b("v-if",!0)]))),128))])]))}}),Z=N(F,[["__file","/home/runner/work/jck/jck/presentation/node_modules/.pnpm/@slidev+client@0.48.8_postcss@8.4.38_vite@5.2.8/node_modules/@slidev/client/pages/presenter/print.vue"]]);export{Z as default};
