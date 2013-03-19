---
title: SXP Contract specification format
permalink: wiki/SXP_Contract_specification_format/
layout: wiki
---

This page describes the technichal information about the SXP Contract
Definition, which is an extension of OASIS Legal XML eContracts.

Required clauses
----------------

These are the clauses that the user must fill in for creating a SXP
Contract:

-   Parties. Actually, this clause exists at the original Legal XML
    Specification (at the *contract-front* level), but it is not
    considered mandatory. In our specification it has been set
    as mandatory.

<!-- -->

-   Breach clause. A clause that specifies what happens in case of one
    party doesn't accomplish the contract. It should include the
    procedure in this case:
    -   the person responsible
    -   the legal authority
    -   the party that should claim the authority

<!-- -->

-   Objects clause. A reference to the objects that will be exchanged.
    This reference will be done under the [Items
    Specification](/wiki/Items_Specification "wikilink").

<!-- -->

-   Delivery clause.
    -   the person who will pay the delivery of the objects
    -   when he will do it

<!-- -->

-   VAT clause. Information about the value-added taxes involved in
    the exchange.
    -   the amount
    -   the authority who will receive the taxes
    -   the parties responsibles of paying

Optional clauses
----------------

Basically, users can add as many clauses as they want as *items*, under
the *body* level. These clauses are intended to contain text that can be
read by both parties.

Minimal example of a SXP Contract
---------------------------------

In this example, we have two parties (Alice and Bob) exchanging objects
(a violin and a guitar).

<?xml version="1.0"?>
`  `<contract xmlns="urn:oasis:names:tc:eContracts:1:0"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="urn:oasis:names:tc:eContracts:1:0 SXPContract.xsd"
   >  
`  `

<title>
<text>Contract between Alice and Bob</text>

</title>
`  `<contract-front>  
`  `<date-block>  
`  `<date>` 20-02-2013`</date>  
`  `</date-block>  
`  `  
`  `  
`  `<parties>  
`  `<party>`Alice`</party>  
`  `<party>`Bob`</party>  
`  `</parties>  
`  `</contract-front>  
`  `  
`  `

<body>
`  `<breachClause>  
`  `<authority>` France jurisdiction`</authority>  
`  `</breachClause>  
`  `<objects>  
`  `<object>` Violin `</object>  
`  `<object>` Guitar `</object>  
`  `</objects>  
`  `<deliveryClause>  
`  `<party>` Bob `</party>  
`  `<date>` 22-2-2013`</date>  
`  `</deliveryClause>  
`  `<vatClause>  
`  `<party>` Alice `</party>  
`  `<amount>` 1.40€ `</amount>  
`  `<authority>` France Revenue Service `</authority>  
`  `

</body>
`  `</contract>