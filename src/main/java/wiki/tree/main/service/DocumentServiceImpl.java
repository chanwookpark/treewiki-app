package wiki.tree.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.tree.main.domain.Document;
import wiki.tree.main.domain.Tag;
import wiki.tree.main.repository.DocumentRepository;
import wiki.tree.main.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static wiki.tree.main.domain.Tag.*;

/**
 * @author chanwook
 */
@Transactional
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository dr;

    @Autowired
    private TagRepository tr;

    @Override
    public void updateDocument(Document doc, String tagString) {
        dr.save(doc);
        saveTag(doc, tagString);
    }

    @Override
    public void createDocument(Document doc, String tagString) {
        Document saved = dr.save(doc);
        List<Tag> tagList = createTag(tagString, saved.getId());
        tr.save(tagList);
    }

    @Override
    public void saveTag(Document doc, String tagString) {
        final List<Tag> savedTag = tr.findByReference(doc.getId());
        Map<String, String> tagMap = createTagMap(savedTag);
        List<String> updateTag = createTagList(tagString);
        List<Tag> createTag = new ArrayList<Tag>();
        for (String tag : updateTag) {
            if (tagMap.containsKey(tag)) {
                tagMap.remove(tag);
            } else {
                createTag.add(new Tag(tag, doc.getId()));
            }
        }

        // 신규 Tag는 저장
        tr.save(createTag);

        // 남은 Tag는 삭제
        tr.delete(createTag(tagMap));
    }
}