INSERT INTO Koie
VALUES ('Flaakoia', '63.56813, 10.29541', 'skog', '11', '0', 'http://org.ntnu.no/koiene/koiene/koiene.php?k=flaakoia' );

INSERT INTO Koie
VALUES ('Fosenkoia', '63.56813, 10.29541', 'skog, tregrense', '10', '0','http://org.ntnu.no/koiene/koiene/koiene.php?k´=fosenkoia' );

INSERT INTO Koie
VALUES ('Heinfjordstua', '63.56813, 10.29541', 'skog', '25', '0','http://org.ntnu.no/koiene/koiene/koiene.php?k=heinfjordstua' );

INSERT INTO Koie
VALUES ('Hognabu', '63.56813, 10.29541', 'tregrense', '6', '0','http://org.ntnu.no/koiene/koiene/koiene.php?k=hognabu' );

INSERT INTO Koie
VALUES ('Holmsaakoia', '63.56813, 10.29541', 'skog', '20', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=holmsaakoia');

INSERT INTO Koie
VALUES ('Holvassgamma', '63.56813, 10.29541', 'skog', '8', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=holvassgamma');

INSERT INTO Koie
VALUES ('Iglbu', '63.56813, 10.29541', 'tregrense', '8', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=iglbu');

INSERT INTO Koie
VALUES ('Kamtjonnkoia', '63.56813, 10.29541', 'hoyfjell', '6', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=kamtjonnkoia');

INSERT INTO Koie
VALUES ('Kraaklikaaten', '63.56813, 10.29541', 'skog', '4', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=kraaklikaaten');

INSERT INTO Koie
VALUES ('Kvernmovollen', '63.56813, 10.29541', 'tregrense', '8', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=kvernmovollen');

INSERT INTO Koie
VALUES ('Kaasen', '63.56813, 10.29541', 'skog', '8', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=kaasen');

INSERT INTO Koie
VALUES ('Lynhogen', '63.56813, 10.29541', 'skog, tregrense', '4', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=lynhogen');

INSERT INTO Koie
VALUES ('Mortenskaaten', '63.56813, 10.29541', 'tregrense', '2', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=mortenskaaten');

INSERT INTO Koie
VALUES ('Nicokoia', '63.56813, 10.29541', 'skog, tregrense', '8', '0','http://org.ntnu.no/koiene/koiene/koiene.php?k=nicokoia' );

INSERT INTO Koie
VALUES ('Rindalsloa', '63.56813, 10.29541', 'skog', '4', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=rindalsloa');

INSERT INTO Koie
VALUES ('Selbukaaten', '63.56813, 10.29541', 'skog', '2', '0','http://org.ntnu.no/koiene/koiene/koiene.php?k=selbukaaten' );

INSERT INTO Koie
VALUES ('Sonvasskoia', '63.56813, 10.29541', 'skog', '8', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=sonvasskoia');

INSERT INTO Koie
VALUES ('Stabburet', '63.56813, 10.29541', 'tregrense', '2', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=stabburet');

INSERT INTO Koie
VALUES ('Stakkslettbua', '63.56813, 10.29541', 'tregrense', '11', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=stakksletbua');

INSERT INTO Koie
VALUES ('Telin', '63.56813, 10.29541', 'tregrense', '9', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=telin');

INSERT INTO Koie
VALUES ('Taagaabu', '63.56813, 10.29541', 'tregrense', '6', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=taagaabu');

INSERT INTO Koie
VALUES ('Vekvessaetra', '63.56813, 10.29541', 'tregrense', '20', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=vekvessaetra');

INSERT INTO Koie
VALUES ('Ovensenget', '63.56813, 10.29541', 'skog, tregrense', '8', '0' ,'http://org.ntnu.no/koiene/koiene/koiene.php?k=ovensenget');

INSERT INTO Reservasjon (epost,datoFra,datoTil,reservertkoieid) 
VALUES ('madelelo@stud.ntnu.no','2014.09.14','2014.09.15','Fosenkoia');

INSERT INTO Reservasjon (epost,datoFra, datoTil,reservertkoieid) 
VALUES ('madelelo@stud.ntnu.no','2014.11.01','2014-11.03','Flaakoia');

INSERT INTO Admin
VALUES ('Madeleine','passord','Madeleine Loras','92885006','madelelo@stud.ntnu.no');

INSERT INTO Admin
VALUES ('Marius','passord','Marius Lundbo','12345678','marius.lundbo@gmail.com');

INSERT INTO Admin
VALUES ('Kathrine','passord','Kathrine Lofqvist','12345678','kathrinelof@gmail.com');

INSERT INTO Admin
VALUES ('Joakim','passord','Joakim Lindgren','12345678','joakigl@stud.ntnu.no');

INSERT INTO Admin
VALUES ('Morten','passord','Morten Lundenes','12345678','morten23_lundenes@hotmail.com');

INSERT INTO Admin
VALUES ('Kristoffer','passord','Kristoffer Lervik','12345678','kristoffer-lervik@hotmail.com');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('badstue','2011.05.01','1','madeleine','Flaakoia');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('baat','2011.05.01','1','madeleine','Flaakoia');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('isbor','2011.05.01','1','Madeleine','Flaakoia');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('badstue','2011.05.01','1','Marius','Heinfjordstua');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('stekeovn','2011.05.01','1','Madeleine','Heinfjordstua');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('badstue','2011.05.01','1','Madeleine','Holmsaakoia');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('piano','2011.05.01','1','Madeleine','Holmsaakoia');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('baat','2011.05.01','1','Madeleine','Holvassgamma');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('isbor','2011.05.01','1','Madeleine','Kamtjonnkoia');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('do','2011.05.01','1','Madeleine','Kvernmovollen');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('olbua','2011.05.01','1','Madeleine','Mortenskaaten');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('hengekoie','2011.05.01','1','Madeleine','Mortenskaaten');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('kano','2011.05.01','1','Madeleine','Sonvasskoia');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('isbor','2011.05.01','1','Madeleine','Sonvasskoia');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('lenestol','2011.05.01','1','Madeleine','Telin');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('sagkrakk','2011.05.01','1','Madeleine','Vekvessaetra');

INSERT INTO Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID)
VALUES ('badstue','2011.05.01','1','Madeleine','Ovensenget');

INSERT INTO Rapport (tekst, gjenglemt, vedstatus, koierapportID)
VALUES ('For en fantastisk fin tur!','lue','0','Flaakoia');
