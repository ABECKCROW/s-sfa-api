

■リンク  
　[フロント(CodoSandbox)](https://codesandbox.io/p/sandbox/s-sfa-jt47pw)  
　[API仕様書(v1.0.0)](https://app.swaggerhub.com/apis/abeckcrow/S-SFA/1.0.0)

***

<details><summary>要件定義書</summary></div>


## S-SFA
**Support Sales Force Automation(営業支援ツール)**<br />
　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　作成者: 安部達朗  
　　　　　　　　　　　　　　　　　　　　　　　　　最終更新日: 2023年12月4日
***
## 内容
1. <details><summary>システム概要</summary><div>
   <br />
   A)　システム構成図<br />
   　　・ER図<br />

   ![ER図(S-SFA).png](img%2FER%E5%9B%B3%28S-SFA%29.png)
   B)　背景<br />
　　・前職で営業活動において使用していたツールが使いづらく、改善したい箇所を今の技術でできる範囲で改善するために作成します。
   <table>
               <thead>
                   <tr>
                       <th>As Is</th>
                       <th>To Be</th>
                   </tr>
               </thead>
               <tbody>
                   <tr>
                       <td>企業によってのランク付ができていない</td>
                       <td>企業のランク付によって関係性がわかる</td>
                   </tr>
                   <tr>
                       <td>アプローチの優先度がわかりにくい</td>
                       <td>アプローチの優先順に表示される</td>
                   </tr>
                   <tr>
                       <td>画面遷移しなくては詳細が見れない</td>
                       <td>同じ画面内で企業情報が見れる</td>
                   </tr>
                   <tr>
                       <td>履歴とアポイント登録が別画面で操作が必要なため履歴の登録漏れが起こる</td>
                       <td>アポイント登録後にそのまま履歴を入力できる</td>
                   </tr>
               </tbody>
           </table>
   　　　<br />
   C)　定義</div>
   　　・営業担当：企業情報を検索し、対応履歴を登録する。<br />
   　　・管理者：企業担当の振り分けをし、対応履歴から行動管理を行う。</details>
2. <details><summary>業務要件</summary><div>
   A)　業務フロー  

   ![業務フロー.png](img%2F%E6%A5%AD%E5%8B%99%E3%83%95%E3%83%AD%E3%83%BC.png)
   B)　規模  
　　・管理者を1人、営業担当5人を想定。<br />
   C)　時期・時間  
　　・プロジェクト開始日：2023年12月<br />
　　・システム稼働日：2024年2月<br />
　　・サービス提供時間24時間<br />
　　・詳細は<a href="https://github.com/users/ABECKCROW/projects/4/views/2">ロードマップ</a>を参照。<br />
   D)　指標<br />
　　・システムの稼働率99.9%<br />
   E)　範囲  
   　　・企業管理と対応履歴管理に焦点を当て、案件管理、売り上げ管理などは対象外とする。</details>
3. <details><summary>機能案件</summary><div>
   A)　機能<br />
   　　・企業管理機能<br />
   　　・対応履歴管理機能<br />
   　　・アポイント日程管理機能<br />
   　　・営業担当管理機能<br />
   　　・詳細は<a href="https://app.swaggerhub.com/apis/MUDSKIPPERMAT/support-sales_force_automation/1.0.0#/">API仕様書(Swagger)</a>を参照。<br />
   B)　画面<br />
   　　・営業担当画面<br />
   　　・管理者画面<br />
   C)　情報・データログ<br />
   　　・対応履歴情報(対応日時、対応内容、企業情報)<br />
   D)　外部インターフェイス<br />
   　　・データベース(情報の永続化)<br /></div></details>
4. <details><summary>非機能要件</summary><div>
   A)　ユーザービリティ及びアクセシビリティ<br />
   　　・直感的で利用しやすいUIを採用<br />
   B)　システム方式<br />
   　　・クラウドベースのWebアプリケーションとデータベースを使用。<br />
   C)　規模<br />
   　　・予想される同時アクセス数：100ユーザー<br />
   D)　性能<br />
   　　・応答時間2秒以内<br />
   　　・データベースへのアクセス時間：5秒以内<br />
   E)　信頼性<br />
   　　・データの冗長性確保：データベースの冗長化を実施。<br />
   F)　拡張性<br />
   　　・新しい管理項目の追加が容易であること。<br />
   G)　上位互換性<br />
   　　・主要なWebブラウザでの動作確認（Google Chrome、Mozilla Firefox、Microsoft Edgeなど）。<br />
   H)　継続性<br />
   　　・データベースへのアクセス時間：5秒以内<br /></div></details>
5. <details><summary>セキュリティ要件</summary><div>
   A)　情報セキュリティ<br />
   　　・顧客情報の暗号化（SSLを使用）<br />
   B)　稼働環境<br />
   　　・クラウドプロバイダー：Amazon Web Services (AWS)<br />
   　　・オペレーティングシステム：Linux<br />
   C)　テスト<br />
   　　・単体テスト<br />
   　　・結合テスト<br /></div></details>
   
6. <details><summary>移行要件</summary><div>
   A)　移行<br />
   　　・無<br />
   B)　引継ぎ</div>
   　　・管理者向けトレーニングセッションの実施<br /></details>
7. <details><summary>運用要件</summary><div>
   A)　教育<br />
      　　・管理者向けトレーニングセッションの提供<br />
   B)　運用<br />
      　　・システムモニタリングと生涯対応体制の構築<br />
      　　・月次レポートの作成<br /></div></details>
   
8. <details><summary>ドキュメント更新履歴</summary><div>
   　　・2023年12月2日: 項目作成<br />
   　　・2023年12月4日: 詳細記載<br />
   
</div></details>



***
