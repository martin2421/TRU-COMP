/*
   Author: Martin Atanacio T00684924
   Date:   1st June 2022

   Filename: functions.js
*/

/* 

   VARIABLE DECLARATION

*/

let births = new Array(); // contains famous birthdays

let currentDate = new Date(); // stores current system's date
let options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }; // sets the format for the Date object

let numberOfDay = 0; // stores the # of day calculated by dayNumber()

/* 

   METHOD CALLING

*/

initializeArray();
showDate();
dayNumber();
showBirthDay();

/* 

   USER DEFINED FUNCTIONS

*/

// Used to display the date in the form "Weekday, Month Day, Year"
function showDate() {
   document.getElementById("userBirthday").innerHTML = currentDate.toLocaleDateString("en-US", options);
}

// Used to calculate the day number (1 - 366) of a given date
function dayNumber() {

   let year = parseInt(currentDate.getFullYear());
   let start = new Date(year, 0, 0);
   let diff = (currentDate - start) + ((start.getTimezoneOffset() - currentDate.getTimezoneOffset()) * 60 * 1000);
   let oneDay = 1000 * 60 * 60 * 24;
   
   numberOfDay = Math.floor(diff / oneDay); // converts into a # from 1 - 366

   // checks that the year is not a leap year
   if (!(year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) {
      if (numberOfDay >= 60) {
         numberOfDay += 1;
      }
   } 
}

// Used to display a famous birthday falling on a given date
function showBirthDay() {
   document.getElementById("famousBirthday").innerHTML = births[numberOfDay];
   console.log(births[numberOfDay]);
}

// Array containg famous birthdays
function initializeArray() {
   births[1] = "J.D. Salinger (1919) - Author";
   births[2] = "Isaac Asimov (1920) - Author";
   births[3] = "Clement Attlee (1883) - 42 Prime Minister";
   births[4] = "Floyd Patterson (1935) - Heavyweight boxer";
   births[5] = "King Camp Gillette (1855) - Invented safety razor";
   births[6] = "Mel Gibson (1956) - Actor";
   births[7] = "Millard Fillmore (1800) - 13th US President";
   births[8] = "Elvis Aaron Presley (1935) - King of rock 'n' roll";
   births[9] = "Richard Nixon (1913) - 37th US President";
   births[10] = "George Foreman (1949) - Heavyweight boxing champ";
   births[11] = "Alexander Hamilton (1755) - American Founding Father";
   births[12] = "Joe Frazier (1947) - Heavyweight boxing champ";
   births[13] = "Orlando Bloom (1977) - Actor";
   births[14] = "Benedict Arnold (1741) - American";
   births[15] = "Martin Luther King (1929) - Civil-rights ";
   births[16] = "Andre Michelin (1853) - Inventor of rubber tires";
   births[17] = "Muhammad Ali (1942) - Boxer Cassius Clay";
   births[18] = "David D Kaminsky (1913) - Danny Kaye";
   births[19] = "James Watt (1736) - Inventor of the steam engine";
   births[20] = "Edwin 'Buzz' Aldrin (1930) - Second man on Moon";
   births[21] = "Telly Savalas (1924) - Actor";
   births[22] = "Sam Cooke (1935) - Singer";
   births[23] = "Princes Caroline (1957) - Royal of Monaco";
   births[24] = "John Belushi (1949) - Actor";
   births[25] = "Robert Burns (1759) - Scottish poet";
   births[26] = "Paul Newman (1925) - Actor";
   births[27] = "Amadeus Mozart (1756) - Composer";
   births[28] = "Elijah Wood (1981) - Actor";
   births[29] = "William McKinley (1843) - American President";
   births[30] = "Franklin D. Roosevelt (1882) - American President";
   births[31] = "Mario Lanza (1921) - Tenor singer"; // Jan 31
   births[32] = "Clark William Gable (1901) - Actor";
   births[33] = "James Joyce (1882) - Author";
   births[34] = "Gertrude Stein (1874) - Author";
   births[35] = "Charles Lindbergh (1902) - Aviator";
   births[36] = "John Boyd Dunlop (1840) - Inventor of the pneumatic tire";
   births[37] = "Ronald Reagan (1911) - 40th US President";
   births[38] = "Charles Dickens (1812) - Author";
   births[39] = "James Dean (1931) - Actor";
   births[40] = "Sandy Lyle (1958) - British golfer";
   births[41] = "Harold Macmillan (1894) - 44th UK Prime Min";
   births[42] = "Thomas Alva Edison (1847) - Inventor of the electric light bulb";
   births[43] = "Abraham Lincoln (1809) - 16th US President";
   births[44] = "George Segal (1934) - Actor";
   births[45] = "Jimmy Hoffa (1913) - Union Leader";
   births[46] = "Galileo Galilei (1564) - Italian astronomer";
   births[47] = "John McEnroe (1959) - Tennis player";
   births[48] = "Michael Jordan (1963) - Basketball player";
   births[49] = "Enzo Ferrari (1898) - Sports car builder";
   births[50] = "Prince Andrew (1960) - Queens 3rd child";
   births[51] = "Sidney Poitier (1927) - First black Oscar win";
   births[52] = "Jilly Cooper (1937) - Author";
   births[53] = "George Washington (1732) - 1st US President";
   births[54] = "Linda Nolan (1959) - Singer"
   births[55] = "Steven Jobs (1955) - Software pioneer";
   births[56] = "George Harrison (1943) - Ex-Beatle guitarist";
   births[57] = "William F Cody (1846) - Buffalo Bill";
   births[58] = "Elizabeth Taylor (1932) - Actress Oscar winner";
   births[59] = "Barry McGuigan (1961) - Featherweight boxer"; // Feb 28
   births[60] = "Gioacchino Rossini (1792) - William Tell Overture"; // Feb 29
   births[61] = "Glen Miller (1904) - Bandleader"; // March 1
   births[62] = "Mikhail Gorbachev (1931) - Soviet President";
   births[63] = "Alexander G Bell (1847) - Inventor of telephone";
   births[64] = "Patrick Moore (1923) - Astronomer";
   births[65] = "William Oughtred (1574) - Inventor of the slide rule";
   births[66] = "Valentine Tereshkova (1937) - First woman in space";
   births[67] = "Armstrong Jones (1930) - Lord Snowdon (photographer)";
   births[68] = "Karl Von Grafe (1787) - Plastic surgery pioneer";
   births[69] = "Yuri Gagarin (1934) - First man in space";
   births[70] = "Chuck Norris (1942) - Karate film actor";
   births[71] = "Sir Harold Wilson (1916) - 46th UK Prime Min";
   births[72] = "Liza Minnelli (1946) - Actress";
   births[73] = "Joseph Priestley (1733) - Discoverer of oxygen gas";
   births[74] = "Albert Einstein (1879) - Theory of  relativity";
   births[75] = "Andrew Jackson (1767) - 7th US President";
   births[76] = "George Ohm (1787) -  Measure of electricity";
   births[77] = "Gottlieb Daimler (1834) - Inventor of the combustion engine";
   births[78] = "Neville Chamberlain (1869) - 40th UK Prime Min";
   births[79] = "David Livingstone (1813) - Explorer";
   births[80] = "Mr. Rodgers (1928) - Children's entertainer";
   births[81] = "Johann Sebastian Bach (1685) - Composer";
   births[82] = "Andrew Lloyd Webber (1948) - British songwriter";
   births[83] = "Sir Roger Bannister (1948) - First runner to break the four-minute mile";
   births[84] = "Steve McQueen (1930) - Actor";
   births[85] = "Reginald K Dwight (1947) - Singer Elton John";
   births[86] = "Diana Ross (1944) - Singer";
   births[87] = "Henry Royce (1863) - Co founder of Rolls-Royce";
   births[88] = "Neil Kinnock (1942) - Former Labour leader";
   births[89] = "John Major (1947) - 50th UK Prime Min";
   births[90] = "Vincent Van Gogh (1854) - Dutch painter";
   births[91] = "Gordie Howe (1928) - Hockey player";
   births[92] = "William Harvey (1578) - Discoverer of blood circulation";
   births[93] = "Hans Chris Andersen (1805) - The Ugly Duckling";
   births[94] = "Doris Kappelhoff (1924) - Doris Day (actress)";
   births[95] = "Maya Angelou (1928) - Poet";
   births[96] = "Lord Joseph Lister (1827) - Introduced antiseptic";
   births[97] = "Harry Houdini (1874) - Escape artist";
   births[98] = "William Wordsworth (1843) - Poet";
   births[99] = "Julian Lennon (1963) - Singer";
   births[100] = "Isambard Brunel (1806) - Engineer of the Thames tunnel";
   births[101] = "Michel Shalhouz (1932) - Actor Omar Sharif ";
   births[102] = "Joel Grey (1932) - Actor";
   births[103] = "Tom Clancy (1947) - Author";
   births[104] = "Garry Kasparov (1963) - British chess champ";
   births[105] = "Rod Steiger (1925) - Acotr";
   births[106] = "Leonardo da Vinci (1452) - Genius";
   births[107] = "Charlie S Chaplin (1889) - Silent comedy actor";
   births[108] = "Nikita Krushchev (1894) - Soviet leader";
   births[109] = "Hayley Mills (1946) - Child star actress";
   births[110] = "Dudley Moore (1935) - Actor 'Arthur' and '10'";
   births[111] = "Adolf Hitler (1889) - German leader";
   births[112] = "Queen Elizabeth II (1926) - Head of British Commonwealth";
   births[113] = "Jack Nicholson (1938) - Actor";
   births[114] = "William Shakespeare (1564) - Playwright and Poet";
   births[115] = "Barbra Streisand (1942) - Singer";
   births[116] = "Ella Fitzgerald (1918) - Singer";
   births[117] = "Jet Li (1963) - Actor";
   births[118] = "Samuel Morse (1791) - Inventor of Morse Code";
   births[119] = "Saddam Hussein (1937) - Deposed Iraq President";
   births[120] = "Hirohito (1901) - Emperor of Japan";
   births[121] = "Kath Smith (1909) - Singer";
   births[122] = "Duke of Wellington (1769) - 23rd UK Prime Min";
   births[123] = "Harry (Bing) Crosby (1904) - Singer/Actor";
   births[124] = "Arnold G Dorsey (1936) - Engelbert Humperdink";
   births[125] = "Michael Barrymore (1952) - Entertainer";
   births[126] = "Tammy Wynette (1942) - Singer";
   births[127] = "Sigmund Freud (1856) - Psychoanalysis";
   births[128] = "Johannes Brahms (1833) - German composer";
   births[129] = "Harry S Truman (1884) - 33rd US President";
   births[130] = "Glenda Jackson (1936) - Actress";
   births[131] = "Fred Astaire (1899) - Actor and tap-dancer";
   births[132] = "Phil Silvers (1912) - Actor";
   births[133] = "Florence Nightingale (1820) - Crimean war nurse";
   births[134] = "Stevie Wonder (1950) - Singer";
   births[135] = "Thomas Gainborough (1727) - Landscape painter";
   births[136] = "James Mason (1909) - Actor";
   births[137] = "Henry Fonda (1905) - Actor";
   births[138] = "Grace Jones (1955) - Singer";
   births[139] = "Karol Wojtyla (1920) - Pope John Paul II";
   births[140] = "Pete Townshend (1945) - Singer";
   births[141] = "James M Stewart (1908) - Comedy actor";
   births[142] = "Leo Sayer (1948) - Singer";
   births[143] = "Laurence Olivier (1907) - Actor";
   births[144] = "Joan Collins (1933) - Film & TV actress";
   births[145] = "Queen Victoria (1819) - Longest reigning Queen";
   births[146] = "Miles Davis (1926) - Musician";
   births[147] = "Marion Morrison (1907) - Actor John Wayne";
   births[148] = "Vincent Price (1911) - Horror movie actor";
   births[149] = "Ian Fleming (1908) - Author";
   births[150] = "John F Kennedy (1917) - 35th US President";
   births[151] = "Peter the Great (1672) - Emperor of Russia";
   births[152] = "Clint Eastwood (1930) - Actor";
   births[153] = "Norma Jean Baker (1926) - Marilyn Monroe";
   births[154] = "Johnny Weissmuller (1903) - Actor";
   births[155] = "King George V (1865) - 2nd son of Edward VII";
   births[156] = "Christopher Cockerel (1910) - Inventor of the hovercraft";
   births[157] = "John Couch Adams (1819) - British Astronomer";
   births[158] = "Robert Falcon Scott (1868) - 1st Brit to South Pole";
   births[159] = "Tom Jones (1940) - Singer";
   births[160] = "Nancy Sinatra (1940) - Singer";
   births[161] = "George Stephenson (1781) - Inventor of the first steam locomotive";
   births[162] = "Judy Garland (1922) - Singer";
   births[163] = "Vince Lombardi (1913) - Coach of the Green Bay Packers";
   births[164] = "George Herbert Bush (1924) - 41st US President";
   births[165] = "Tim Allen (1953) - Actor";
   births[166] = "Boy George (1961) - Singer ";
   births[167] = "Mario Cuomo (1932) - Former governor of New York";
   births[168] = "Stan Laurel (1890) - Laurel & Hardy duo";
   births[169] = "Barry Manilow (1946) - Singer";
   births[170] = "Paul McCartney (1942) - Singer";
   births[171] = "Blaise Pascal (1623) - French mathematician";
   births[172] = "Errol Flynn (1909) - Action-adventure film";
   births[173] = "Prince William (1982) - Prince & Princess Wales son";
   births[174] = "Meryl Streep (1949) - Actress";
   births[175] = "Charles R Darwin (1809) - Theory of evolution";
   births[176] = "Jack Dempsey (1895) - Heavyweight boxing";
   births[177] = "Eric Arthur Blair (1903) - George Orwell";
   births[178] = "William T Kelvin (1827) - Inventor of the absolute temperature scale";
   births[179] = "Helen Keller (1880) - Author";
   births[180] = "Mel Brooks (1926) - Writer/Actor";
   births[181] = "Nelson Eddy (1901) - Singer";
   births[182] = "Mike Tyson (1966) - Boxer";
   births[183] = "Dianna Spencer (1961) - Princess Diane";
   births[184] = "Richard Petty (1937) - Racecar driver";
   births[185] = "Tom Cruise (1962) - Actor";
   births[186] = "Calvin Coolidge (1872) - American president";
   births[187] = "Bjorn Borg (1980) - Wimbledon tennis champion";
   births[188] = "Bill Haley (1925) - Singer";
   births[189] = "Richard Starkey (1940) - Ringo Starr";
   births[190] = "Ringo Starr (1940) - Ex-Beatle drummer";
   births[191] = "Sir Edward Heath (1916) - 47th UK Prime Min";
   births[192] = "Virginia Wade (1945) - Tennis champion";
   births[193] = "Yul Brynner (1917) - Actor";
   births[194] = "Josiah Wedgwood (1730) - Blue & white pottery";
   births[195] = "Harrison Ford (1942) - Actor";
   births[196] = "Gerald Rudolph Ford (1913) - 38th US President";
   births[197] = "Rembrandt (1606) - Dutch artist & painter";
   births[198] = "Roald Amundsen (1872) - First man to reach the South Pole";
   births[199] = "James Cagney (1899) - Actor";
   births[200] = "John Glenn (1921) - First man to orbit Earth";
   births[201] = "Samuel Colt (1814) - Inventor of the colt revolver";
   births[202] = "Sir Edmund Hillary (1919) - First to climb Mt. Everest";
   births[203] = "Ernest Hemingway (1899) - Author";
   births[204] = "Bob Dole (1923) - Former U.S. Senator";
   births[205] = "Monica Lewinsky (1973)";
   births[206] = "Amelia Earhart (1898) - 1st woman to fly Atlantic";
   births[207] = "Arthur James Balfour (1848) - 33rd UK Prime Min";
   births[208] = "Mick Jagger (1944) - Singer";
   births[209] = "Joseph Hilary Belloc (1870) - Author";
   births[210] = "Beatrix Potter (1866) - Author";
   births[211] = "Peter Jennings (1938) - Journalist";
   births[212] = "Emily Bronte (1818) - Wuthering Heights";
   births[213] = "J. K. Rowling (1965) - Author";
   births[214] = "Herman Melville (1819) - Author";
   births[215] = "Peter O'Toole (1932) - Lawrence of Arabia";
   births[216] = "Stanly Baldwin (1868) - 38th UK Prime Min";
   births[217] = "Queen Elizabeth (1900) - King George 6th wife";
   births[218] = "Neil Armstrong (1930) - First man on the Moon";
   births[219] = "Andy Warhol (1928) - Artist";
   births[220] = "Mata Hari (1876) - Spy for the Germans";
   births[221] = "Dustin Hoffman (1937) - Actor";
   births[222] = "Thomas Telford (1757) - Road & bridge Engineer";
   births[223] = "Herbert Clark Hoover (1874) - 31st US President";
   births[224] = "Enid Blyton (1897) - Author";
   births[225] = "King George 4th (1762) - King of England";
   births[226] = "John Logie Baird (1888) - Inventor of television";
   births[227] = "John Galsworthy (1867) - Author";
   births[228] = "Napoleon Bonaparte (1769) - French leader";
   births[229] = "Madonna (1959) - Singer";
   births[230] = "May West (1892) - Actress";
   births[231] = "Robert Redford (1937) - Actor";
   births[232] = "Orville Wright (1871) - Inventor of the first airplane";
   births[233] = "Jim Reeves (1924) - Singer";
   births[234] = "William Murdock (1754) - Inventor of coal-gas lighting in 1792";
   births[235] = "Ray Bradbury (1920) - Author";
   births[236] = "Gene Kelly (1912) - Actor/Dancer";
   births[237] = "Craig Kilborn (1962) - Actor";
   births[238] = "Sean Connery (1930) - Actor";
   births[239] = "Robert Walpole (1676) - 1st UK Prime Minister";
   births[240] = "Sam Goldwyn (1910) - Film producer";
   births[241] = "David Soul (1828) - Actor";
   births[242] = "Michael Jackson (1958) - Singer";
   births[243] = "Denis Healey (1917) - Politician";
   births[244] = "Sir Bernard Lovell (1928) - Inventor of the radio telescope";
   births[245] = "Rocky Marciano (1923) - Heavyweight boxer";
   births[246] = "Salma Hayek (1968) - Actress";
   births[247] = "Alan Ladd (1913) - Actor";
   births[248] = "Tom Watson (1949) - Golfer";
   births[249] = "Raquel Welch (1940) - Actress";
   births[250] = "Britt Ekland (1942) - Actress";
   births[251] = "Queen Elizabeth I (1533) - Queen of England";
   births[252] = "King Richard I (1157) - Richard the Lion Heart";
   births[253] = "William Bligh (1754) - Mutiny on the Bounty";
   births[254] = "Arnold Palmer (1929) - US golfing champion";
   births[255] = "O. Henry (1862) - Author";
   births[256] = "Richard Gatling (1818) - Inventor of the Gatling Gun";
   births[257] = "Claudette Colbert (1905) - Actress";
   births[258] = "Jack Hawkins (1910) - British film actor";
   births[259] = "Agatha Christie (1891) - Detective storywriter";
   births[260] = "B. B. King (1925) - Musician";
   births[261] = "John Ritter (1948) - Actor";
   births[262] = "Samuel Johnson (1709) - 18th century writer";
   births[263] = "George Cadbury (1839) - Chocolate manufacturer";
   births[264] = "Sophia Loren (1934) - Italian film actress";
   births[265] = "H G Wells (1866) - Science fiction novelist";
   births[266] = "Mickey Rooney (1920) - American actor";
   births[267] = "Julio Iglesias (1943) - Singer";
   births[268] = "Anthony Newley (1931) - Actor";
   births[269] = "Meat Loaf (1947) - Singer";
   births[270] = "George Gershwin (1898) - Composer";
   births[271] = "Mike Schmidt (1949) - Baseball player";
   births[272] = "Lech Walesa (1943) - Founder of Solidarity";
   births[273] = "Horatio Nelson (1758) - Commander";
   births[274] = "Johnny Mathis (1935) - Singer";
   births[275] = "Jimmy Carter (1924) - 39th American President";
   births[276] = "Mahatma K Gandhi (1869) - Hindu spiritual leader";
   births[277] = "Chubby Checker (1941) - Singer";
   births[278] = "Buster Keaton (1895) - Silent film comedy actor";
   births[279] = "Donnald Pleasence (1919) - Actor";
   births[280] = "George Westinghouse (1846) - Inventor of the railway air brake";
   births[281] = "Yo-Yo Ma (1955) - Musician";
   births[282] = "R. L. Stine (1943) - Author";
   births[283] = "John Lennon (1940) - Beatles song writer";
   births[284] = "William Morris (1877) - Motoring pioneer";
   births[285] = "Henry J Heins (1844) - Food products manufacturer";
   births[286] = "Ramsay MacDonald (1866) - 39th British Prime Minister";
   births[287] = "Margaret H Thatcher (1925) - First woman Prime Minister";
   births[288] = "Dwight Eisenhower (1890) - 34th American President";
   births[289] = "John L. Sullivan (1858) - World heavyweight boxing champion";
   births[290] = "Noah Webster (1758) - American Webster dictionary";
   births[291] = "Evel Knievel (1938) - Motorcycle Stuntman";
   births[292] = "Lee Harvey Oswald (1939) - Shot President Kennedy";
   births[293] = "Evander Holyfield (1962) - Heavyweight boxer";
   births[294] = "Christopher Wren (1632) - Architect of St Pauls Cathedral";
   births[295] = "Alfred Nobel (1833) - Inventor of dynamite in 1867";
   births[296] = "Franz Liszt (1811) - Hungarian composer";
   births[297] = "Pele (1940) - Brazilian footballer";
   births[298] = "Moss Hart (1904) - Playwright";
   births[299] = "Pablo Picasso (1881) - Spanish painter";
   births[300] = "Francois Mitterrand (1916) - President of France";
   births[301] = "Captain James Cook (1728) - Pacific Ocean explorer";
   births[302] = "Cleo Laine (1927) - Singer";
   births[303] = "Edmund Halley (1656) - Halley's Comet astronomer";
   births[304] = "John Adams (1735) - 2nd American President 1797";
   births[305] = "Dan Rather (1931) - Newsreader";
   births[306] = "Gary Player (1935) - Golfer";
   births[307] = "Burt Lancaster (1913) - Actor";
   births[308] = "Lulu (1948) - Singer";
   births[309] = "William III (1650) - King of England";
   births[310] = "Art Garfunkel (1941) - Singer";
   births[311] = "Adolphe Sax (1854) - Inventor of the Saxophone";
   births[312] = "Joni Mitchell (1943) - Singer";
   births[313] = "Christian Barnard (1922) - Heart transplant pioneer";
   births[314] = "Katherine Hepburn (1909) - Actress";
   births[315] = "Tim Rice (1944) - Actor";
   births[316] = "Kurt Vonnegut, Jr. (1922) - Author";
   births[317] = "Grace Patricia Kelly (1929) - Princess Grace of Monaco";
   births[318] = "Charles Bronson (1922) - Actor";
   births[319] = "Prince Charles (1948) - Charles Philip Arthur George";
   births[320] = "William Herschel (1738) - Discovered planet Uranus 1781";
   births[321] = "Frank Bruno (1961) - Boxer and TV personality";
   births[322] = "Viscount Montgomery (1887) - World War II commander";
   births[323] = "Alan Shephard (1923) - First American man in space";
   births[324] = "Indira Gandhi (1917) - Prime Minister of India";
   births[325] = "Bobby Locke (1917) - South African golf champion";
   births[326] = "Harpo Marx (1888) - Marx brother";
   births[327] = "Charles de Gaulle (1890) - President of France";
   births[328] = "Boris Karloff (1887) - Horror film actor";
   births[329] = "Ian Botham (1955) - England all-round cricketer";
   births[330] = "Karl Benz (1844) - German motor car pioneer";
   births[331] = "William Cowper (1731) - English poet";
   births[332] = "Jimi Hendrix (1942) - Musician";
   births[333] = "Friedrich Engels (1820) - Associate of Karl Marx";
   births[334] = "C. S. Lewis (1898) - Author";
   births[335] = "Winston Churchill (1874) - Prime Minister";
   births[336] = "Madame Marie Tussaud (1761) - Founder of waxworks museum";
   births[337] = "Georges Seurat (1859) - Painter";
   births[338] = "Jeff Bridges (1949) - Actor";
   births[339] = "General Franco (1892) - Spanish Head of State";
   births[340] = "Walter Elias Disney (1901) - Cartoon film producer";
   births[341] = "Henry VI (1421) - Henry V only child";
   births[342] = "Victor Kiam (1926) - Business tycoon";
   births[343] = "Mary Queen of Scots (1542) - Queen Elizabeth I cousin";
   births[344] = "Clarence Birdseye (1886) - Frozen food pioneer";
   births[345] = "John Dewey (1851) - Library system classification";
   births[346] = "Ludwig Van Beethoven (1770) - Composer";
   births[347] = "Frank Sinatra (1915) - Singer";
   births[348] = "Jim Davidson (1954) - Comedian";
   births[349] = "Michel de Nostredame (1503)";
   births[350] = "Alexandre G Eiffel (1832) - Designer of the Eiffel Tower";
   births[351] = "Catherine of Aragon (1485) - The first wife of Henry VIII";
   births[352] = "Tommy Steele (1936) - Singer";
   births[353] = "Cassius Clay (1942) - Heavyweight boxing champion";
   births[354] = "Leonid Brezhnev (1906) - Soviet President 1977-1982";
   births[355] = "Uri Geller (1946) - Psychic";
   births[356] = "Jane Fonda (1937) - Actress";
   births[357] = "Maurice & Robin Gibb (1949) - Singers";
   births[358] = "Richard Arkwright (1732) - Inventor of cotton spinning";
   births[359] = "Ava Gardner (1922) - American film actress";
   births[360] = "Sir Isaac Newton (1642) - Scientist & mathematician";
   births[361] = "Mao Tse-Tung (1893) - Leader of Chinese Communist Party";
   births[362] = "Louis Pasteur (1822) - Bacteriologist & chemist";
   births[363] = "Woodrow Wilson (1856) - 28th American President 1913";
   births[364] = "Jude Law (1972) - Actor";
   births[365] = "Rudyard Kipling (1865) - Author";
   births[366] = "Bonnie Prince Charlie (1720) - Attempted to seize England";

}








