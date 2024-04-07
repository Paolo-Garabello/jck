import{_ as l}from"./CodeBlockWrapper-DX-zv-yE.js";import{o as e,c as n,k as a,l as r,m as p,q as E,s as d,A as h,e as s,a0 as i}from"../modules/vue-BNkoxSqV.js";import{I as A}from"./default-B3Dt9U5H.js";import{b as g,K as k}from"../index-oshRG3ZJ.js";import{p as D,u as y,f as B}from"./context-B5J5u7v4.js";import"../modules/unplugin-icons-D4R0xC7_.js";import"../modules/shiki-B7STecTN.js";const o=s("h1",null,"Il Database",-1),c=s("p",null,"Abbiamo scelto di usare il database SQLite, ecco la sua struttura:",-1),C=s("pre",{class:"shiki shiki-themes vitesse-dark vitesse-light slidev-code",style:{"--shiki-dark":"#dbd7caee","--shiki-light":"#393a34","--shiki-dark-bg":"#121212","--shiki-light-bg":"#ffffff"}},[s("code",{class:"language-sql"},[s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}},"CREATE"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," TABLE"),s("span",{style:{"--shiki-dark":"#80A665","--shiki-light":"#59873A"}}," IF"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," NOT"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," EXISTS"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}}," users (")]),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},"  id "),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}},"INTEGER"),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}}," PRIMARY KEY"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," NOT NULL"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},",")]),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},"  username "),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}},"VARCHAR"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},"("),s("span",{style:{"--shiki-dark":"#4C9A91","--shiki-light":"#2F798A"}},"128"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},") "),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}},"NOT NULL"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," UNIQUE"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},",")]),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}},"  password"),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}}," VARCHAR"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},"("),s("span",{style:{"--shiki-dark":"#4C9A91","--shiki-light":"#2F798A"}},"256"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},") "),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}},"NOT NULL")]),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},");")]),i(`
`),s("span",{class:"line"}),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}},"CREATE"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," TABLE"),s("span",{style:{"--shiki-dark":"#80A665","--shiki-light":"#59873A"}}," IF"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," NOT"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," EXISTS"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}}," messages (")]),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},"  id "),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}},"INTEGER"),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}}," PRIMARY KEY"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," NOT NULL"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},",")]),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}},"  text"),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}}," TEXT"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," NOT NULL"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},",")]),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},"  sender UNSIGNED "),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}},"INT"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," NOT NULL"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},",")]),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},"  recipient UNSIGNED "),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}},"INT"),s("span",{style:{"--shiki-dark":"#4D9375","--shiki-light":"#1E754F"}}," NOT NULL"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},",")]),i(`
`),s("span",{class:"line"}),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}},"  FOREIGN KEY"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}}," (sender) "),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}},"REFERENCES"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}}," users(id)")]),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}},"  FOREIGN KEY"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}}," (recipient) "),s("span",{style:{"--shiki-dark":"#CB7676","--shiki-light":"#AB5959"}},"REFERENCES"),s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}}," users(id)")]),i(`
`),s("span",{class:"line"},[s("span",{style:{"--shiki-dark":"#DBD7CAEE","--shiki-light":"#393A34"}},");")])])],-1),N={__name:"5",setup(_){return D(k),y(),(m,F)=>{const t=l;return e(),n(A,E(d(h(B)(h(k),4))),{default:a(()=>[o,c,r(t,p({},{ranges:[]}),{default:a(()=>[C]),_:1},16)]),_:1},16)}}},U=g(N,[["__file","/@slidev/slides/5.md"]]);export{U as default};
